package pages.basePage;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.cartPage.CartPage;
import pages.myAccountPage.MyAccountPage;
import pages.shopPage.ShopPage;

import static com.codeborne.selenide.Selenide.page;

public class BasePage {
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'My account')]")
    protected SelenideElement myAccount;
    @FindBy(how = How.XPATH, using = "//a[@class='cart-contents']")
    protected SelenideElement cart;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Shop')]")
    protected SelenideElement shop;


    public MyAccountPage goToMyAccount(){
        myAccount.click();
        return page(MyAccountPage.class);
    }

    public CartPage goToCart(){
        cart.click();
        return page(CartPage.class);
    }

    public ShopPage goToShop(){
        shop.click();
        return page(ShopPage.class);
    }
}
