package pages.cartPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.basePage.BasePage;
import pages.checkOutPage.CheckOutPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;

public class CartPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//a[@class='checkout-button button alt wc-forward']")
    private SelenideElement proceedToCheckOutButton;

    @FindBy(how = How.XPATH, using = "//tr[@class='order-total']//span[@class='woocommerce-Price-amount amount']")
    private SelenideElement totalPrice;
    @FindBy(how = How.ID, using = "coupon_code")
    private SelenideElement couponCode;
    @FindBy(how = How.NAME, using = "apply_coupon")
    private SelenideElement applyCouponButton;
    @FindBy(how = How.XPATH, using = "//div[@class='woocommerce-message']")
    private SelenideElement message;
    @FindBy(how = How.XPATH, using = "//*[@class='blockUI blockOverlay']")
    private SelenideElement overlay;
    @FindBy(how = How.XPATH, using = "//input[@type='number']")
    private ElementsCollection quantities;
    @FindBy(how = How.XPATH, using = "//button[@name='update_cart']")
    private SelenideElement updateCart;

    public CartPage checkTotalSum(String sum){
        overlay.waitWhile(Condition.visible, 50000);
        totalPrice.shouldHave(text(sum));
        return this;
    }

    public CartPage setQuantity(String ...quantity){
        for (SelenideElement number :
                quantities) {
            number.val(quantity[quantities.indexOf(number)]);
        }
        updateCart.click();
        return this;
    }

    public CheckOutPage proceedToCheckOut(){
        overlay.waitWhile(Condition.visible, 50000);
        proceedToCheckOutButton.click();
        return new CheckOutPage();
    }

    public CartPage applyCoupon(String coupon){
        overlay.waitWhile(Condition.visible, 50000);
        couponCode.val(coupon);
        applyCouponButton.click();
        return this;
    }

    public CartPage checkApplyCouponMessage(){
        message.shouldHave(Condition.matchesText("Coupon code applied successfully."));
        return this;
    }
}
