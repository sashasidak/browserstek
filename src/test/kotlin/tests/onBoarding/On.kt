package android

import io.appium.java_client.MobileBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.MalformedURLException
import java.net.URL

object BrowserStackSample {
    @Throws(MalformedURLException::class, InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val caps = DesiredCapabilities()

        // Set your access credentials
        caps.setCapability("browserstack.user", "testabank1")
        caps.setCapability("browserstack.key", "jxaBHX8UzqyRxWs8PKjL")

        // Set URL of the application under test
        caps.setCapability("app", "bs://c9a6e31e18282a2697a51317972655e97011ee8e")

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3")
        caps.setCapability("os_version", "9.0")

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project")
        caps.setCapability("build", "browserstack-build-1")
        caps.setCapability("name", "first_test")


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        val driver = AndroidDriver<AndroidElement>(
            URL("http://hub.browserstack.com/wd/hub"), caps
        )


        // Test case for the BrowserStack sample Android app.
        // If you have uploaded your app, update the test case here.
        val searchElement = WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(
                MobileBy.AccessibilityId("Search Wikipedia")
            )
        ) as AndroidElement
        searchElement.click()
        val insertTextElement = WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(
                MobileBy.id("org.wikipedia.alpha:id/search_src_text")
            )
        ) as AndroidElement
        insertTextElement.sendKeys("BrowserStack")
        Thread.sleep(5000)
        val allProductsName = driver.findElementsByClassName(
            "android.widget.TextView"
        )
        assert(allProductsName.size > 0)


        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit()
    }
}