package pages.cartPage;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.checkOutPage.CheckOutPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;

public class CartPage {
    @FindBy(how = How.XPATH, using = "//a[@class='checkout-button button alt wc-forward']")
    private SelenideElement proceedToCheckOutButton;

    @FindBy(how = How.XPATH, using = "//tr[@class='order-total']//span[@class='woocommerce-Price-amount amount']")
    private SelenideElement totalPrice;

    public CartPage checkTotalSum(String sum){
        totalPrice.shouldHave(text(sum));
        return this;
    }

    public CheckOutPage proceedToCheckOut(){
        proceedToCheckOutButton.click();
        return page(CheckOutPage.class);
    }
}
