package pages.checkOutPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.text;

public class OrderReceiptPage {
    @FindBy(how = How.XPATH, using = "//*[@class='woocommerce-order-overview__payment-method method']/strong")
    private SelenideElement paymentMethod;

    public OrderReceiptPage checkPaymentMethod(String name){
        paymentMethod.waitUntil(Condition.appear, 15000);
        paymentMethod.shouldHave(text(name));
        return this;
    }
}
