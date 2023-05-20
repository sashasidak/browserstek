package tests.onBoarding

import common.helpers.AuthHelper
import org.testng.annotations.Test
import tests.base.BaseTest
import tests.base.applicationManager

class OnboardingTest : BaseTest() {

    @Test
    fun sendAssetToAnotherWallet() {
        applicationManager.getHelper<AuthHelper>()
            .fullAuth()
            .clickOn()
    }
}

