package common

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.appium.ScreenObject.screen
import com.google.common.collect.ImmutableMap
import constants.Constants
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType.*
import io.appium.java_client.remote.MobileCapabilityType.*
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.FileReader
import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


open class ApplicationManager {

    private var driver: AppiumDriver<*>? = null
    private val clipboard = Selenide.clipboard()
    var formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var date: Date = Date()

    @JvmName("getDriver1")
    fun createDriver(deviceIndex: String):  AppiumDriver<*>{
        var driver: AppiumDriver<*>? = null

        when (Constants.RunVariables.PLATFORM) {
                Constants.Platform.IOS -> {
        val capabilitiesIOS = DesiredCapabilities()
        val parser = JSONParser()
        val config =
            parser.parse(FileReader("src/test/resources/iOS_parallel.conf.json")) as JSONObject
        val envs = config["environments"] as JSONArray

        val envCapabilities = envs[deviceIndex.toInt()] as Map<String, String>
        var it: Iterator<*> = envCapabilities.entries.iterator()
        while (it.hasNext()) {
            val (key, value) = it.next() as Map.Entry<*, *>
            capabilitiesIOS.setCapability(key.toString(), value.toString())
        }
        val commonCapabilities = config["capabilities"] as Map<String, String>
        it = commonCapabilities.entries.iterator()
        while (it.hasNext()) {
            val (key, value) = it.next() as Map.Entry<*, *>
            if (capabilitiesIOS.getCapability(key.toString()) == null) {
                capabilitiesIOS.setCapability(key.toString(), value)
            }
        }
        val commonCapabilitiesNext = config["capabilities"] as Map<String, String>
        it = commonCapabilitiesNext.entries.iterator()
        while (it.hasNext()) {
            val (key, value) = it.next() as Map.Entry<*, *>
            if (capabilitiesIOS.getCapability(key.toString()) == "browserstack-build-1") {
                capabilitiesIOS.setCapability(key.toString(),  formater.format(date).toString())
            }
        }
        var username = System.getenv("BROWSERSTACK_USERNAME")
        if (username == null) {
            username = config["username"] as String?
        }
        var accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY")
        if (accessKey == null) {
            accessKey = config["access_key"] as String?
        }
        val app = System.getenv("BROWSERSTACK_APP_ID")
        if (app != null && !app.isEmpty()) {
            capabilitiesIOS.setCapability("app", app)
        }
        val url = URL("http://" + username + ":" + accessKey + "@" + config["server"] + "/wd/hub")
                    if (driver == null) {
                        driver = IOSDriver<SelenideElement>(url, capabilitiesIOS)
                    }
                    driver
                }



            Constants.Platform.AOS -> {
                val capabilitiesAOS = DesiredCapabilities()
                val parser = JSONParser()
                val config =
                    parser.parse(FileReader("src/test/resources/AOS_parallel.conf.json")) as JSONObject
                val envs = config["environments"] as JSONArray

                val envCapabilities = envs[deviceIndex.toInt()] as Map<String, String>
                var it: Iterator<*> = envCapabilities.entries.iterator()
                while (it.hasNext()) {
                    val (key, value) = it.next() as Map.Entry<*, *>
                    capabilitiesAOS.setCapability(key.toString(), value.toString())
                }
                val commonCapabilities = config["capabilities"] as Map<String, String>
                it = commonCapabilities.entries.iterator()
                while (it.hasNext()) {
                    val (key, value) = it.next() as Map.Entry<*, *>
                    if (capabilitiesAOS.getCapability(key.toString()) == null) {
                        capabilitiesAOS.setCapability(key.toString(), value)
                    }
                }
                val commonCapabilitiesNext = config["capabilities"] as Map<String, String>
                it = commonCapabilitiesNext.entries.iterator()
                while (it.hasNext()) {
                    val (key, value) = it.next() as Map.Entry<*, *>
                    if (capabilitiesAOS.getCapability(key.toString()) == "browserstack-build-1") {
                        capabilitiesAOS.setCapability(key.toString(),  formater.format(date).toString())
                    }
                }
                var username = System.getenv("BROWSERSTACK_USERNAME")
                if (username == null) {
                    username = config["username"] as String?
                }
                var accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY")
                if (accessKey == null) {
                    accessKey = config["access_key"] as String?
                }
                val app = System.getenv("BROWSERSTACK_APP_ID")
                if (app != null && !app.isEmpty()) {
                    capabilitiesAOS.setCapability("app", app)
                }
                val url = URL("http://" + username + ":" + accessKey + "@" + config["server"] + "/wd/hub")

                if (driver == null) {
                    driver = AndroidDriver<SelenideElement>(url, capabilitiesAOS)
                }
                driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS)
                driver
            }

            }
//        } catch (e: MalformedURLException) {
//            e.printStackTrace()
//        }
//        throw IllegalArgumentException("Cannot detect type of the Driver. Platform value: " + "name")
        return driver
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