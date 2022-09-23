package pages

import PageSetup
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory
import java.util.*

class HomePage(driver: WebDriver?) : PageSetup() {

    @FindBy(xpath = "//a[@class='ico-register']")
    private val registerField: WebElement? = null

    @FindBy(id = "Email")
    private val emailField: WebElement? = null

    @FindBy(id = "FirstName")
    private val firstNameField: WebElement? = null

    @FindBy(id = "LastName")
    private val lastNameField: WebElement? = null

    @FindBy(id = "Password")
    private val passwordField: WebElement? = null

    @FindBy(id = "ConfirmPassword")
    private val confirmPasswordField: WebElement? = null

    @FindBy(id = "register-button")
    private val registerButton: WebElement? = null

    @FindBy(xpath = "//a[contains(text(),'Electronics')]")
    private val electronicsTab: WebElement? = null

    @FindBy(xpath = "//img[@title='Show products in category Cell phones']")
    private val cellPhoneOption: WebElement? = null

    @FindBy(xpath = "//img[@title='Show details for HTC One Mini Blue']")
    private val item: WebElement? = null

    @FindBy(xpath = "//div[@class='description'][contains(text(),'HTC One Mini')]/..//button[@class='button-2 product-box-add-to-cart-button']")
    private val addToCartButton: WebElement? = null

    @FindBy(id = "topcartlink")
    private val cartLink: WebElement? = null

    @FindBy(id = "termsofservice")
    private val acceptTerms: WebElement? = null

    @FindBy(id = "checkout")
    private val checkoutButton: WebElement? = null

    @FindBy(id = "BillingNewAddress_CountryId")
    private val countryDropdown: WebElement? = null

    @FindBy(xpath = "//option[@value='212']")
    private val specificCountry: WebElement? = null

    @FindBy(id = "BillingNewAddress_City")
    private val cityField: WebElement? = null

    @FindBy(id = "BillingNewAddress_Address1")
    private val addressField: WebElement? = null

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private val zipCodeField: WebElement? = null

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private val phoneNumberField: WebElement? = null

    @FindBy(xpath = "//button[@class='button-1 new-address-next-step-button']")
    private val continueButtonAddress: WebElement? = null

    @FindBy(id = "shippingoption_1")
    private val nextDayAirShipmentOption: WebElement? = null

    @FindBy(xpath = "//button[@class='button-1 shipping-method-next-step-button']")
    private val continueButtonShipment: WebElement? = null

    @FindBy(id = "paymentmethod_1")
    private val creditCardOption: WebElement? = null

    @FindBy(xpath = "//button[@class='button-1 payment-method-next-step-button']")
    private val continueButtonPayment: WebElement? = null

    @FindBy(xpath = "//button[@class='button-1 payment-info-next-step-button']")
    private val continueButtonPaymentInfo: WebElement? = null

    @FindBy(id = "CardholderName")
    private val cardholderNameField: WebElement? = null

    @FindBy(id = "CardNumber")
    private val cardNumberField: WebElement? = null

    @FindBy(id = "ExpireYear")
    private val expirationYearField: WebElement? = null

    @FindBy(xpath = "//option[@value='2024']")
    private val expirationYearOption: WebElement? = null

    @FindBy(id = "CardCode")
    private val cvcField: WebElement? = null

    @FindBy(xpath = "//button[@class='button-1 confirm-order-next-step-button']")
    private val confirmOrderButton: WebElement? = null

    @FindBy(xpath = "//a[contains(text(),'Click here for order details')]")
    private val confirmationOrderText: WebElement? = null

    init {
        PageFactory.initElements(AjaxElementLocatorFactory(driver, 10), this)
    }

    val isConfirmationOrderTextDisplayed: Boolean get() = isElementDisplayed(confirmationOrderText)

    fun registerUser(
        userName: String,
        password: String,
        firstName: String,
        lastName: String,
        driver: WebDriver
    ) {
        clickOnElement(registerField)
        fillElementField(firstNameField, firstName)
        fillElementField(lastNameField, lastName)
        fillElementField(emailField, userName)
        scrollToBottom(driver)
        fillElementField(passwordField, password)
        fillElementField(confirmPasswordField, password)
        clickOnElement(registerButton)
    }

    fun clickOnElectronicsTab() {
        clickOnElement(electronicsTab)
    }

    fun clickOnCellPhoneOption() {
        clickOnElement(cellPhoneOption)
    }

    fun isItemDisplayed(driver: WebDriver): Boolean {
        scrollToBottom(driver)
        return isElementDisplayed(item)
    }

    fun addItemToCart() {
        clickOnElement(addToCartButton)
    }

    fun navigateToCart(driver: WebDriver) {
        scrollToTop(driver)
        Thread.sleep(4000)
        clickOnElement(cartLink)
    }

    fun acceptTermsAndProceedToCheckout(driver: WebDriver) {
        scrollToBottom(driver)
        clickOnElement(acceptTerms)
        clickOnElement(checkoutButton)
    }

    fun selectCountry() {
        clickOnElement(countryDropdown)
        clickOnElement(specificCountry)
    }

    fun addCity() {
        fillElementField(cityField, "Random City")
    }

    fun addAddress(driver: WebDriver) {
        scrollToBottom(driver)
        fillElementField(addressField, "Random Address")
    }

    fun addZipCode() {
        fillElementField(zipCodeField, "12345")
    }

    fun addPhoneNumber() {
        fillElementField(phoneNumberField, "123456789")
    }

    fun clickContinueOnAddress() {
        clickOnElement(continueButtonAddress)
    }

    fun clickContinueOnShipment() {
        clickOnElement(continueButtonShipment)
    }

    fun selectNextDayAirOption() {
        clickOnElement(nextDayAirShipmentOption)
    }

    fun selectCreditCardOption() {
        clickOnElement(creditCardOption)
    }

    fun clickContinueOnPayment() {
        clickOnElement(continueButtonPayment)
    }

    fun fillCardInfo(name: String, cardNumber: String) {
        fillElementField(cardholderNameField, name)
        fillElementField(cardNumberField, cardNumber)
        clickOnElement(expirationYearField)
        clickOnElement(expirationYearOption)
        fillElementField(cvcField, "123")
        clickOnElement(continueButtonPaymentInfo)
    }

    fun confirmOrder() {
        clickOnElement(confirmOrderButton)
    }
}
