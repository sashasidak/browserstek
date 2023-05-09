package tests.base

import com.codeborne.selenide.WebDriverRunner
import common.ApplicationManager
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod


var applicationManager: ApplicationManager = ApplicationManager()

open class BaseTest {

    @BeforeMethod
    fun createDriver() {
        WebDriverRunner.setWebDriver(applicationManager.createDriver())
    }

    @AfterMethod()
    fun closeApplication() {
        WebDriverRunner.closeWebDriver()
    }

}