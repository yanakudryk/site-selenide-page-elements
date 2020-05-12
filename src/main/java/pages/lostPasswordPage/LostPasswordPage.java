package pages.lostPasswordPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import entities.User;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class LostPasswordPage {
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
    public void checkLostPasswordMessage(){
        message.shouldHave(Condition.matchesText("Password reset email has been sent."));
    }

}
