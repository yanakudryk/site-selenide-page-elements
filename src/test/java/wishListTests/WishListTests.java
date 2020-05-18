package wishListTests;

import baseTests.BaseTests;
import com.github.javafaker.Faker;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.shopPage.ShopPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.*;

public class WishListTests extends BaseTests {
    protected ShopPage shopPage;

    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = new ShopPage();
    }
    @Test
    public void testAddToWishList() {
        Faker usFaker = new Faker(new Locale("en-US"));
        User user = new User(
                usFaker.name().username().concat(MAIL_DOMAIN),
                PASSWORD,
                usFaker.name().firstName(),
                usFaker.name().lastName());

        shopPage.
                goToMyAccount().
                register(user).
                goToShop().
                openProduct(PRODUCT_NAME).
                addToWishList().
                checkThatProductIsAddedToWishList(PRODUCT_NAME).
                addAllToCart().
                checkAddToCartMessage(PRODUCT_NAME);
    }

    @Test
    public void testRemoveFromWishList() {
        Faker usFaker = new Faker(new Locale("en-US"));
        User user = new User(
                usFaker.name().username().concat(MAIL_DOMAIN),
                PASSWORD,
                usFaker.name().firstName(),
                usFaker.name().lastName());

        shopPage.
                goToMyAccount().
                register(user).
                goToShop().
                openProduct(PRODUCT_NAME).
                addToWishList().
                goToShop().
                openProduct(PRODUCT_NAME).
                removeFromWishList().
                checkThatProductIsRemovedFromWishList();
    }
}
