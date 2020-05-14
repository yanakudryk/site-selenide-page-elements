package pages.lostPasswordPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.basePage.BasePage;

public class PasswordResetPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//input[@id='password_1']")
    private SelenideElement newPassword;
    @FindBy(how = How.XPATH, using = "//input[@id='password_2']")
    private SelenideElement reEnteredNewPassword;
    @FindBy(how = How.XPATH, using = "//button[@class='woocommerce-Button button']")
    private SelenideElement saveButton;
    @FindBy(how = How.XPATH, using = "//div[@class='woocommerce-message']")
    private SelenideElement message;

    public PasswordResetPage setNewPassword(String password){
        newPassword.val(password);
        reEnteredNewPassword.val(password);
        saveButton.click();
        return this;
    }

    public void checkResetPasswordMessage(){
        message.shouldHave(Condition.matchesText("Your password has been reset successfully."));
    }
}
