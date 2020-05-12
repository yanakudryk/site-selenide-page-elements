package registrationAndLoginAndLogoutTests;

import baseTests.BaseTests;
import entities.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.mailPage.MailPage;
import pages.shopPage.ShopPage;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.*;
import static credentials.MailCredentials.MAIL_EMAIL_2;
import static credentials.MailCredentials.MAIL_PASSWORD;

public class LoginTests extends BaseTests {
    protected ShopPage shopPage;
    protected MailPage mailPage;
    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = page(ShopPage.class);
        mailPage = page(MailPage.class);
    }

    @Test
    public void testLogin(){
        User user = new User(MAIL_EMAIL_2, PASSWORD, FIRST_NAME, LAST_NAME);
        shopPage.
                goToMyAccount().
                login(user, false).
                checkMyAccountMessage(FIRST_NAME);
    }

    @Test
    public void testLostPassword(){
        User user = new User(MAIL_EMAIL_2, PASSWORD, FIRST_NAME, LAST_NAME);

        shopPage.
                goToMyAccount().
                lostPassword().
                resetPassword(user).
                checkLostPasswordMessage();

        mailPage.
                followPasswordResetLinkFromMail(MAIL_EMAIL_2, MAIL_PASSWORD).
                setNewPassword(PASSWORD).
                checkResetPasswordMessage();
    }

    @Test
    public void testLogOut(){
        User user = new User(MAIL_EMAIL_2, PASSWORD, FIRST_NAME, LAST_NAME);
        shopPage.
                goToMyAccount().
                login(user, false).
                logOut().
                checkUserIsNotLoggedIn();
    }
}
