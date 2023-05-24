package tests.onBoarding

import common.TestRail.TestRails
import common.helpers.AuthHelper
import org.testng.annotations.Test
import tests.base.BaseTest
import tests.base.applicationManager

class OnboardingTest : BaseTest() {

    @Test
    @TestRails(id = "3")
    fun sendAssetToAnotherWallet() {
        applicationManager.getHelper<AuthHelper>()
            .fullAuth()
            .clickOn()
    }
}

