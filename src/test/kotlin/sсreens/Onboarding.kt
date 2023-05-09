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
import kotlin.test.assertEquals

class Onboarding : AnyScreen() {

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'No, I do not')]")
//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=No]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"No\"]")
    private val dontHaveAmmerCard: SelenideElement? = null

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Create')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Create\"]")
    private val createWalletText: SelenideElement? = null

    @AndroidFindBy(id = "trustody.wallet:id/btnSettings")
//    @iOSXCUITFindBy(accessibility = "")
    private val cardSettingsButton: SelenideElement? = null

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Remove card')]")
//    @iOSXCUITFindBy(accessibility = "")
    private val removeCard: SelenideElement? = null

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
//    @iOSXCUITFindBy(accessibility = "")
    private val receiveButton: SelenideElement? = null

    //    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"1\"]")
    private val numberOne: SelenideElement? = null//

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"2\"]")
    private val numberTwo: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"3\"]")
    private val numberThree: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"4\"]")
    private val numberFour: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"5\"]")
    private val numberFive: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"6\"]")
    private val numberSix: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(accessibility = "Set app passcode")
    private val setPasscodeText: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(accessibility = "Confirm app passcode")
    private val confirmPasscodeText: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"settingsIcon\"]")
    private val settingsButton: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Send\"]")
    private val sendButton: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"0.00\"]")
    private val amountInput: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value=\"Address\"]")
    private val addressInput: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Confirm\"]")
    private val confirmButton: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(accessibility = "Success")
//    @iOSXCUITFindBy(accessibility = "Transaction signed")
    private val successSendForm: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(accessibility = "Transaction signed")
//    @iOSXCUITFindBy(accessibility = "Transaction signed")
    private val transactionSigned: SelenideElement? = null

    // @AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Receive')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"OK\"]")
    private val okButton: SelenideElement? = null

    fun setPassCode(): Onboarding {
        setPasscodeText?.isDisplayed
        numberOne?.click()
        numberTwo?.click()
        numberThree?.click()
        numberFour?.click()
        numberFive?.click()
        numberSix?.click()
        return applicationManager.getScreen()
    }

    fun confirmPassCode(): Onboarding {
        confirmPasscodeText?.isDisplayed
        numberOne?.click()
        numberTwo?.click()
        numberThree?.click()
        numberFour?.click()
        numberFive?.click()
        numberSix?.click()
        return applicationManager.getScreen()
    }

    fun clickOnDontHaveAmmerCard(): Onboarding {
        implicitlyWait(2)
        dontHaveAmmerCard?.click()
        return applicationManager.getScreen()
    }

    fun clickOnCreateButton(): Onboarding {
        createWalletText?.click()
        return applicationManager.getScreen()
    }

    fun clickOnSettingsButton(): Onboarding {
        implicitlyWait(5)
        settingsButton?.click()
        return applicationManager.getScreen()
    }

    fun scrollToAttachCardScreen(): Onboarding {
        aosScroll(ScrollBy.toText("Attach your plastic card or create a virtual one"))
        return applicationManager.getScreen()
    }

    fun goToAsset(asset: String): Onboarding {
        implicitlyWait(6)
        scrollToText(asset)
        when (Constants.RunVariables.PLATFORM) {
            Constants.Platform.AOS -> Selenide.`$`(By.xpath("//android.widget.TextView[contains(@text, '$asset')]"))
                .click()

            Constants.Platform.IOS -> Selenide.`$`(By.xpath("//XCUIElementTypeStaticText[@name=\"$asset\"]")).click()
        }
        return applicationManager.getScreen()
    }

    fun goToSendAsset(): Onboarding {
        sendButton?.click()
        return applicationManager.getScreen()
    }

    fun setAmount(amount: String): Onboarding {
        amountInput?.shouldBe(Condition.visible)
        amountInput?.value = amount
        return applicationManager.getScreen()
    }

    fun setAddress(address: String): Onboarding {
        addressInput?.shouldBe(Condition.visible)
        addressInput?.value = address
        return applicationManager.getScreen()
    }

    fun clickConfirm(): Onboarding {
        implicitlyWait(1)
        confirmButton?.click()
        return applicationManager.getScreen()
    }

    fun checkSuccessForm(): Onboarding {
        implicitlyWait(3)
        successSendForm?.shouldBe(Condition.visible)
        transactionSigned?.shouldBe(Condition.visible)
        return applicationManager.getScreen()
    }

    fun clickOkButton(): Onboarding {
        okButton?.click()
        return applicationManager.getScreen()
    }

    fun clickOnReceiveButton(): Onboarding {
        receiveButton?.click()
        return applicationManager.getScreen()
    }

    fun goToCardSettings(): Onboarding {
        try {
            if (cardSettingsButton?.isDisplayed == true) {
                cardSettingsButton.click()
                removeCard?.click()
            }
        } catch (e: NoSuchElementException) {
        }
        return applicationManager.getScreen()
    }

    fun checkAddressIsCorrect(): Onboarding {
        val getAddress =
            Selenide.`$`(By.xpath("//android.widget.TextView[contains(@text, '0xae0b880b5c3335c4ac24726c7db5d90fd1c2f76e')]"))
        assertEquals("0xae0b880b5c3335c4ac24726c7db5d90fd1c2f76e", getAddress.getAttribute("name"))
        return applicationManager.getScreen()
    }


}