package sсreens

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import common.helpers.WaitsHelper.implicitlyWait
import constants.Constants
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import org.openqa.selenium.By
import sсreens.base.AnyScreen
import tests.base.applicationManager
import java.time.Duration
import kotlin.test.assertEquals

class Onboarding : AnyScreen() {

    @AndroidFindBy(id = "ua.com.abank:id/tvText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"No\"]")
    private val dontHaveAmmerCard: SelenideElement? = null

    fun clickOnDontHaveAmmerCard(): Onboarding {
        implicitlyWait(2)
        dontHaveAmmerCard?.shouldBe(Condition.visible, Duration.ofSeconds(5))
        return applicationManager.getScreen()
    }


}