package pages.shopPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.basePage.BasePage;
import pages.cartPage.CartPage;
import pages.myAccountPage.MyAccountPage;
import pages.productPage.ProductPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.refresh;

public class ShopPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//ul[contains(@class,'products columns-4')]/li")
    private List<Product> products;

    @FindBy(how = How.XPATH, using = "//ul[@class='product-categories']/li")
    private List<Category> categories;

    @FindBy(how = How.XPATH, using = "//input[@id='s']")
    private SelenideElement search;


    @FindBy(how = How.XPATH, using = "//*[@class='page-numbers']/li[last()]")
    private SelenideElement nextPage;
    @FindBy(how = How.XPATH, using = "//*[@class = 'woocommerce-message']")
    private SelenideElement message;
    @FindBy(how = How.XPATH, using = "//h1[@class='page-title']")
    private SelenideElement pageTitle;
    @FindBy(how = How.XPATH, using = "//p[@class='woocommerce-result-count']")
    private SelenideElement resultCount;

    public ShopPage addProductToCart(String name) {
        for (Product p :
                products) {
            if (p.productTitle.has(text(name))) {
                p.productButton.click();
                break;
            }
        }
        return new ShopPage();
    }

    public ProductPage openProduct(String name){
        for (Product p :
                products) {
            if (p.productTitle.has(text(name))) {
                p.productTitle.click();
                break;
            }
        }
        return new ProductPage();
    }

    public ShopPage checkAddToCartMessage(String name){
        message.should(Condition.matchesText("“" + name + "” has been added to your cart."));
        return this;
    }

    public ShopPage selectCategory(String name){
        for (Category c :
                categories) {
            if (c.categoryName.has(text(name))){
                c.categoryName.click();
                return page(ShopPage.class);
            }
        }
        return new ShopPage();
    }

    public ShopPage checkShopCategory(String name){
        pageTitle.shouldHave(text(name));
        return this;
    }

    public ShopPage searchProduct(String productName){
        search.val(productName).pressEnter();
        return new ShopPage();
    }

    public void checkSearchResults(String name){
        for (Product p :
                products) {
            p.productTitle.shouldHave(text(name));
        }
    }

    public void checkProductsCategories(String name){
        for (Product p :
                products) {
            p.productCategory.shouldHave(text(name));
        }
    }

}
