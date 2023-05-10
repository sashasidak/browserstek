package common

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.appium.ScreenObject.screen
import com.google.common.collect.ImmutableMap
import common.helpers.DataReader.getValue
import constants.Constants
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType.*
import io.appium.java_client.remote.MobileCapabilityType.*
import org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME
import org.openqa.selenium.remote.DesiredCapabilities
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.TimeUnit

open class ApplicationManager {

    private var driver: AppiumDriver<*>? = null
    private val clipboard = Selenide.clipboard()

    @JvmName("getDriver1")
    fun createDriver(): AppiumDriver<*> {
        var driver: AppiumDriver<*>? = null
        try {
            val url = URL("http://hub.browserstack.com/wd/hub")
            return when (Constants.RunVariables.PLATFORM) {
                Constants.Platform.IOS -> {
                    if (driver == null) {
                        driver = IOSDriver<SelenideElement>(url, iOSDesiredCapabilities)
                    }
                    driver
                }

                Constants.Platform.AOS -> {
                    if (driver == null) {
                        driver = AndroidDriver<SelenideElement>(url, androidDesiredCapabilities)
                    }
                    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS)
                    driver
                }
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        throw IllegalArgumentException("Cannot detect type of the Driver. Platform value: " + "name")
    }

    private val androidDesiredCapabilities: DesiredCapabilities
        get() {
            val desiredCapabilities = DesiredCapabilities()
            desiredCapabilities.setCapability(AUTO_GRANT_PERMISSIONS, true)
            desiredCapabilities.setCapability("browserstack.user", "testabank1")
            desiredCapabilities.setCapability("browserstack.key", "jxaBHX8UzqyRxWs8PKjL")

            // Set URL of the application under test
            desiredCapabilities.setCapability("app", "bs://c9a6e31e18282a2697a51317972655e97011ee8e")

            // Specify device and os_version for testing
            desiredCapabilities.setCapability("device", "Google Pixel 3");
            desiredCapabilities.setCapability("os_version", "9.0");

            // Set other BrowserStack capabilities
            desiredCapabilities.setCapability("project", "First Java Project");
            desiredCapabilities.setCapability("build", "browserstack-build-2");
            desiredCapabilities.setCapability("name", "next_test");
            return desiredCapabilities
        }

    private val iOSDesiredCapabilities: DesiredCapabilities
        get() {
            val desiredCapabilities = DesiredCapabilities()
            desiredCapabilities.setCapability(DEVICE_NAME, getValue("IOS_DEVICE_NAME"))
            desiredCapabilities.setCapability("automationName", "XCUITest")
            desiredCapabilities.setCapability(PLATFORM_VERSION, getValue("IOS_PLATFORM_VERSION"))
            desiredCapabilities.setCapability(PLATFORM_NAME, "iOS")
            desiredCapabilities.setCapability(UDID, getValue("IOS_UDID"))
            desiredCapabilities.setCapability("autoAcceptAlerts", true)
            desiredCapabilities.setCapability("bundleId", "io.trustody.wallet")
            desiredCapabilities.setCapability("browserstack.user", "testabank1")
            desiredCapabilities.setCapability("browserstack.key", "jxaBHX8UzqyRxWs8PKjL")
            return desiredCapabilities
        }

    fun getDriver(): AppiumDriver<*> {
        return WebDriverRunner.getWebDriver() as AppiumDriver<*>
    }

    inline fun <reified T> getHelper(): T {
        val clazz: Class<*>?
        val constructor: Constructor<*>?
        var `object`: Any? = null
        try {
            clazz = T::class.java
            constructor = clazz.getConstructor(ApplicationManager::class.java)
            `object` = constructor.newInstance(this)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return `object` as T
    }

    inline fun <reified T> getScreen(): T {
        return screen(T::class.java)
    }

    fun terminateApp() {
        when (Constants.RunVariables.PLATFORM) {
            Constants.Platform.IOS -> getDriver().terminateApp("io.trustody.wallet")
            Constants.Platform.AOS -> driver?.executeScript(
                "mobile: terminateApp",
                ImmutableMap.of("bundleId", "trustody.wallet")
            )
        }
    }

    fun launchApp() {
        when (Constants.RunVariables.PLATFORM) {
            Constants.Platform.IOS -> getDriver().launchApp()
            Constants.Platform.AOS -> driver?.executeScript(
                "mobile: launchApp",
                ImmutableMap.of("bundleId", "trustody.wallet")
            )
        }
    }

    fun setClipboardText(text: String) {
        clipboard.text = text
    }

    fun getClipboardText(): String {
        return clipboard.text
    }
}