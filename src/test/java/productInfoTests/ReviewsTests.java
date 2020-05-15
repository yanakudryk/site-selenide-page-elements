package productInfoTests;

import baseTests.BaseTests;
import com.github.javafaker.Faker;
import entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.shopPage.ShopPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.*;

public class ReviewsTests extends BaseTests {
    protected ShopPage shopPage;

    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = page(ShopPage.class);
    }
    @Test
    public void testAddProductReview(){
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
                goToReviews().
                addReview(PRODUCT_REVIEW_STARS, PRODUCT_REVIEW_COMMENT).
                checkReview(PRODUCT_REVIEW_STARS, PRODUCT_REVIEW_COMMENT);

    }
}
