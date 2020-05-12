package pages.cartPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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
    @FindBy(how = How.ID, using = "coupon_code")
    private SelenideElement couponCode;
    @FindBy(how = How.NAME, using = "apply_coupon")
    private SelenideElement applyCouponButton;
    @FindBy(how = How.XPATH, using = "//div[@class='woocommerce-message']")
    private SelenideElement message;
    @FindBy(how = How.XPATH, using = "//*[@class='blockUI blockOverlay']")
    private SelenideElement overlay;

    public CartPage checkTotalSum(String sum){
        overlay.waitWhile(Condition.visible, 50000);
        totalPrice.shouldHave(text(sum));
        return this;
    }

    public CheckOutPage proceedToCheckOut(){
        proceedToCheckOutButton.click();
        return page(CheckOutPage.class);
    }

    public CartPage applyCoupon(String coupon){
        couponCode.val(coupon);
        applyCouponButton.click();
        return this;
    }

    public CartPage checkApplyCouponMessage(){
        message.shouldHave(Condition.matchesText("Coupon code applied successfully."));
        return this;
    }
}
