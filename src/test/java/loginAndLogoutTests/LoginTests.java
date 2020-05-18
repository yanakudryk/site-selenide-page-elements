package loginAndLogoutTests;

import baseTests.BaseTests;
import entities.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pages.mailPage.MailPage;
import pages.shopPage.ShopPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.*;
import static credentials.MailCredentials.MAIL_EMAIL_2;
import static credentials.MailCredentials.MAIL_PASSWORD;

public class LoginTests extends BaseTests {
    protected ShopPage shopPage;
    //protected MailPage mailPage;
    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = new ShopPage();
        //mailPage = new MailPage();
    }

    @Test
    public void testValidLogin(){
        User user = new User(MAIL_EMAIL_2, PASSWORD, FIRST_NAME, LAST_NAME);
        shopPage.
                goToMyAccount().
                login(user, false).
                checkMyAccountMessage(FIRST_NAME);
    }

    @Test
    public void testInvalidLogin(){
        User user = new User(MAIL_EMAIL_2, INVALID_PASSWORD, FIRST_NAME, LAST_NAME);
        shopPage.
                goToMyAccount().
                login(user,false).
                checkIncorrectPasswordOnLoginErrorMessage(MAIL_EMAIL_2);
    }

    @Test
    public void testLostPassword(){
        User user = new User(MAIL_EMAIL_2, PASSWORD, FIRST_NAME, LAST_NAME);

        shopPage.
                goToMyAccount().
                lostPassword().
                resetPassword(user).
                checkLostPasswordMessage().
                followPasswordResetLinkFromMail().
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
