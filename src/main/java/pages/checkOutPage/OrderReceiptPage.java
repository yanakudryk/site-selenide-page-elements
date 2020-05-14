package pages.checkOutPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.basePage.BasePage;

import static com.codeborne.selenide.Condition.text;

public class OrderReceiptPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@class='woocommerce-order-overview__payment-method method']/strong")
    private SelenideElement paymentMethod;
    @FindBy(how = How.XPATH, using = "//*[@class='woocommerce-order-overview__total total']/strong")
    private SelenideElement totalPrice;

    public OrderReceiptPage checkPaymentMethod(String name){
        paymentMethod.shouldHave(text(name));
        return this;
    }
    public OrderReceiptPage checkTotalSum(String name){
        totalPrice.shouldHave(text(name));
        return this;
    }
}
