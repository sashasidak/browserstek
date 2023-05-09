package tests.onBoarding

import common.helpers.AuthHelper
import org.testng.annotations.Test
import tests.base.BaseTest
import tests.base.applicationManager
import java.lang.Thread.sleep

class OnboardingTest : BaseTest() {

    private val ammerCoinAddress = "0x3fce85be47111f98650cb2a94fb86ca227db676a"

    @Test
    fun sendAssetToAnotherWallet() {
        applicationManager.getHelper<AuthHelper>()
            .fullAuth()
            .clickOnDontHaveAmmerCard()
            .clickOnCreateButton()
            .setPassCode()
            .confirmPassCode()
            .goToAsset("Ammer Coin")
            .goToSendAsset()
            .setAmount("1")
            .setAddress(ammerCoinAddress)
            .clickConfirm()
            .checkSuccessForm()
            .clickOkButton()
        sleep(5000)
    }
}

