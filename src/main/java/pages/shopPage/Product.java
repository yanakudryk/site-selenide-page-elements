package pages.shopPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class Product extends ElementsContainer{


    @FindBy(how = How.XPATH, using = "div//*[@class = 'woocommerce-loop-product__title']")
    protected SelenideElement  productTitle;

    @FindBy(how = How.XPATH, using = "div/*[contains(@class, 'button product_type')]")
    protected SelenideElement  productButton;

    @FindBy(how = How.XPATH, using = "div//*[@class = 'woocommerce-Price-amount amount']")
    protected SelenideElement  productPriceAmount;

    @FindBy(how = How.XPATH, using = "div//*[@class = 'zta-woo-product-category']")
    protected SelenideElement  productCategory;

    @FindBy(how = How.XPATH, using = "div//*[@class = 'tinvwl_add_to_wishlist-text']")
    protected SelenideElement productAddToWishlistButton;

}
