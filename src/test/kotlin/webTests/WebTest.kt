package webTests

import PageSetup
import org.openqa.selenium.WebDriver
import org.testng.Assert.assertTrue
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import pages.HomePage
import java.util.UUID

class WebTest : PageSetup() {

    private var webDriver: WebDriver? = null
    private lateinit var homePage: HomePage

    @BeforeClass
    fun driverSetup() {
        webDriver = getDriver()
    }

    @Test
    fun registerUser() {
        homePage = HomePage(webDriver)
        homePage.registerUser(
            USERNAME,
            PASSWORD,
            FIRST_NAME,
            LAST_NAME,
            webDriver!!
        )
    }

    @Test(priority = 1)
    fun searchItemAndAddToCart() {
        homePage.clickOnElectronicsTab()
        homePage.clickOnCellPhoneOption()
        assertTrue(homePage.isItemDisplayed(webDriver!!))
        homePage.addItemToCart()
    }

    @Test(priority = 2)
    fun proceedToCheckout() {
        homePage.navigateToCart(webDriver!!)
        homePage.acceptTermsAndProceedToCheckout(webDriver!!)
    }

    @Test(priority = 3)
    fun fillBillingAddress() {
        homePage.selectCountry()
        homePage.addCity()
        homePage.addAddress(webDriver!!)
        homePage.addZipCode()
        homePage.addPhoneNumber()
        homePage.clickContinueOnAddress()
    }

    @Test(priority = 4)
    fun fillShipmentAndPaymentInformation() {
        homePage.selectNextDayAirOption()
        homePage.clickContinueOnShipment()
        homePage.selectCreditCardOption()
        homePage.clickContinueOnPayment()
        homePage.fillCardInfo("$FIRST_NAME $LAST_NAME", CARD_NUMBER.toString())
        homePage.confirmOrder()
        assertTrue(homePage.isConfirmationOrderTextDisplayed)
    }

    private companion object {
        val USERNAME = "test${UUID.randomUUID().toString().substring(0, 4)}@test.com"
        const val PASSWORD = "aA1111111#"
        val FIRST_NAME = "First${UUID.randomUUID().toString().substring(0, 4)}"
        val LAST_NAME = "Last${UUID.randomUUID().toString().substring(0, 4)}"
        const val CARD_NUMBER = 4242424242424242
    }
}
