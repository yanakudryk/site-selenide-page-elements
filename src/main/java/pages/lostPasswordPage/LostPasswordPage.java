package pages.lostPasswordPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import entities.User;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.basePage.BasePage;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static utils.ReadEmail.getUrlsFromMessage;

public class LostPasswordPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//input[@id='user_login']")
    private SelenideElement username;
    @FindBy(how = How.XPATH, using = "//button[text()='Reset password']")
    private SelenideElement resetButton;
    @FindBy(how = How.XPATH, using = "//div[@class='woocommerce-message']")
    private SelenideElement message;

    public LostPasswordPage resetPassword(User user){
        username.val(user.getUsername());
        resetButton.click();
        return this;
    }
    public LostPasswordPage checkLostPasswordMessage(){
        message.shouldHave(Condition.matchesText("Password reset email has been sent."));
        return this;
    }

    public PasswordResetPage followPasswordResetLinkFromMail() {
        try {

            List<String> urls =
                    getUrlsFromMessage("Password Reset Request for site",
                            "Click here to reset your password");
            String resetPasswordLink = urls.get(0).replace("amp;", "");
            open(resetPasswordLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PasswordResetPage();
    }
}
