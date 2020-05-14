package pages.wishListPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.basePage.BasePage;

public class WishListPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//td[@class='product-name']")
    private ElementsCollection productsNames;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Add All to Cart')]")
    private SelenideElement addAllToCartButton;
    @FindBy(how = How.XPATH, using = "//*[@class = 'woocommerce-message']")
    private SelenideElement message;

    public WishListPage addAllToCart(){
        addAllToCartButton.click();
        return this;
    }

    public WishListPage checkAddToCartMessage(String name){
        message.should(Condition.matchesText("“" + name + "” has been added to your cart."));
        return this;
    }
}
