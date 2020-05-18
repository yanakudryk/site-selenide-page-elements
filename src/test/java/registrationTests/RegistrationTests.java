package registrationTests;

import baseTests.BaseTests;
import com.github.javafaker.Faker;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.basePage.BasePage;
import pages.shopPage.ShopPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.MAIL_DOMAIN;
import static credentials.Constants.PASSWORD;

public class RegistrationTests extends BaseTests {

    protected ShopPage shopPage;

    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = new ShopPage();
    }

    @Test
    public void testRegistration(){
        Faker usFaker = new Faker(new Locale("en-US"));
        User user = new User(
                usFaker.name().username().concat(MAIL_DOMAIN),
                PASSWORD,
                usFaker.name().firstName(),
                usFaker.name().lastName());

        shopPage.
                goToMyAccount().
                register(user).
                checkMyAccountMessage(user.getUsername().replace(MAIL_DOMAIN, ""));
    }
}
