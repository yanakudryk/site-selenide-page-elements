package pages.checkOutPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class PayPalPage {
    @FindBy(how = How.XPATH, using = "//input[@id='email']")
    private SelenideElement email;
    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    private SelenideElement password;
    @FindBy(how = How.XPATH, using = "//button[@id='btnLogin']")
    private SelenideElement loginButton;
    @FindBy(how = How.XPATH, using = "//input[@id='confirmButtonTop']")
    private SelenideElement payNowButton;

    public PayPalPage loginToPayPal(String email, String password){
        this.email.val(email);
        this.password.val(password);
        loginButton.click();
        return this;
    }
    public OrderReceiptPage clickPayNow(){
        payNowButton.click();
        return page(OrderReceiptPage.class);
    }
}
