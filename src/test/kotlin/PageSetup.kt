import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass

open class PageSetup {

    private lateinit var _driver: WebDriver
    private val url = "https://demo.nopcommerce.com"

    @BeforeClass(alwaysRun = true)
    fun setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver.exe")
        _driver = ChromeDriver()
        _driver.get(url)
        _driver.manage().window().maximize()
    }

    @AfterClass
    fun tearDownDriver() {
        _driver.quit()
    }

    fun getDriver(): WebDriver {
        return this._driver
    }

    fun clickOnElement(webElement: WebElement?) {
        Thread.sleep(1000)
        webElement!!.click()
    }

    fun fillElementField(webElement: WebElement?, inputText: String) {
        Thread.sleep(1000)
        webElement!!.sendKeys(inputText)
    }

    fun isElementDisplayed(webElement: WebElement?): Boolean {
        return try {
            webElement!!.isDisplayed
        } catch (exception: Throwable) {
            false
        }
    }

    fun scrollToBottom(driver: WebDriver) {
        Thread.sleep(1000)
        val js = driver as JavascriptExecutor
        js.executeScript("window.scrollBy(0, 350)")
    }

    fun scrollToTop(driver: WebDriver) {
        Thread.sleep(1000)
        val js = driver as JavascriptExecutor
        js.executeScript("window.scrollBy(0, -350)")
    }
}
