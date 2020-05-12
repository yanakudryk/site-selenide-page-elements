package pages.checkOutPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import entities.BillingDetails;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class CheckOutPage {
    @FindBy(how = How.XPATH, using = "//input[@id='billing_first_name']")
    private SelenideElement firstName;
    @FindBy(how = How.XPATH, using = "//input[@id='billing_last_name']")
    private SelenideElement lastName;
    @FindBy(how = How.XPATH, using = "//input[@id='billing_company']")
    private SelenideElement company;
    @FindBy(how = How.XPATH, using = "//input[@id='billing_address_1']")
    private SelenideElement address_1;
    @FindBy(how = How.XPATH, using = "//input[@id='billing_address_2']")
    private SelenideElement address_2;
    @FindBy(how = How.XPATH, using = "//input[@id='billing_city']")
    private SelenideElement city;
    @FindBy(how = How.XPATH, using = "//select[@id='billing_state']")
    private SelenideElement state;
    @FindBy(how = How.XPATH, using = "//input[@id='billing_postcode']")
    private SelenideElement postcode;
    @FindBy(how = How.XPATH, using = "//input[@id='billing_phone']")
    private SelenideElement phone;
    @FindBy(how = How.XPATH, using = "//input[@id='billing_email']")
    private SelenideElement email;
    @FindBy(how = How.XPATH, using = "//input[@id='createaccount']")
    private SelenideElement createAccountCheckBox;
    @FindBy(how = How.XPATH, using = "//input[@id='account_password']")
    private SelenideElement password;
    @FindBy(how = How.XPATH, using = "//ul[@class='wc_payment_methods payment_methods methods']//input")
    private SelenideElement paymentRadioButton;
    @FindBy(how = How.XPATH, using = "//button[text()='Place order']")
    private SelenideElement placeOrderButton;
    @FindBy(how = How.XPATH, using = "//button[text()='Proceed to PayPal']")
    private SelenideElement proceedToPayPalButton;
    @FindBy(how = How.XPATH, using = "//*[@class='blockUI blockOverlay']")
    private SelenideElement overlay;

    @FindBy(how = How.XPATH, using = "//div[@class='woocommerce-privacy-policy-text']")
    private SelenideElement policy;

    public CheckOutPage fillBillingDetails(BillingDetails billingDetails) {
        firstName.val(billingDetails.getFirstName());
        lastName.val(billingDetails.getLastName());
        company.val(billingDetails.getCompany());
        address_1.val(billingDetails.getAddress_1());
        address_2.val(billingDetails.getAddress_2());
        city.val(billingDetails.getCity());
        state.selectOption(billingDetails.getState());
        postcode.val(billingDetails.getPostcode());
        phone.val(billingDetails.getPhone());
        email.val(billingDetails.getEmail());
        createAccountCheckBox.setSelected(billingDetails.getCreateAccount());
        password.val(billingDetails.getPassword());
        paymentRadioButton.selectRadio(billingDetails.getPayment());
        return this;
    }

    public OrderReceiptPage placeOrder(){
        placeOrderButton.click();
        return page(OrderReceiptPage.class);
    }

    public PayPalPage proceedToPayPal(){
        proceedToPayPalButton.click();
        return page(PayPalPage.class);
    }



}
