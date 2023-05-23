package tests.base

import common.MyTelegramBot
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult


class TestListener : ITestListener {
    private var successCount = 0
    private var failedCount = 0
    private var skipCount = 0

    override fun onTestStart(result: ITestResult) {

    }

    override fun onTestSkipped(result: ITestResult) {
        println("Skipped: " + result.name)
        incrementSkipCount()
    }

    override fun onTestSuccess(result: ITestResult) {
        println("Success: " + result.name)
        incrementSuccessCount()
    }

    override fun onTestFailure(result: ITestResult) {
        incrementFailedCount()
    }

    override fun onTestFailedButWithinSuccessPercentage(result: ITestResult) {
        println("Broken: " + result.name)
    }

    override fun onStart(result: ITestContext?) {
        println("-------------- START --------------")
    }

    override fun onFinish(context: ITestContext?) {
        println("-------------- FINISH --------------")
        sendResultTelegram()
    }

    private fun sendResultTelegram() {
        val botToken = "5883152658:AAFJUFvuzncaVG0HBaS60pL0ak1pXhA7Guk"
        val chatId = "303973918"
        val results = "=======================================================\nРезультаты тестов: Success $successCount, Failed $failedCount, Skip $skipCount\n======================================================="

        val bot = MyTelegramBot(botToken)
        bot.sendMessage(chatId, results)
    }

    fun incrementSuccessCount() {
        successCount++
    }
    fun incrementFailedCount() {
        failedCount++
    }
    fun incrementSkipCount() {
        skipCount++
    }
}