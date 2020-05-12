package pages.mailPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.lostPasswordPage.PasswordResetPage;
import static com.codeborne.selenide.Selenide.*;

public class MailPage {
    @FindBy(how = How.NAME, using = "username")
    private SelenideElement username;
    @FindBy(how = How.NAME, using = "password")
    private SelenideElement password;
    @FindBy(how = How.ID, using = "header-mail-button")
    private SelenideElement mailIcon;
    @FindBy(how = How.XPATH, using = "//*[@data-test-smartview-type='UNREAD']")
    private SelenideElement unreadMessages;
    @FindBy(how = How.XPATH, using = "//*[@data-test-id='message-subject']")
    private SelenideElement message;
    @FindBy(how = How.XPATH, using = "//*[@data-test-id='message-group-subject-text']")
    private SelenideElement messageSubject;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Click here to reset your password')]")
    private SelenideElement resetPasswordLink;


    private void openLastMailMessageFromMail(String email, String password){
        executeJavaScript("window.open()");
        switchTo().window(1);
        open("https://login.yahoo.com/");
        this.username.val(email).pressEnter();
        this.password.val(password).pressEnter();
        mailIcon.click();
        unreadMessages.click();
        while(!message.exists()){
            sleep(25000);
            refresh();
            sleep(25000);
        }
        message.click();
    }

    public PasswordResetPage followPasswordResetLinkFromMail(String email, String password){
        openLastMailMessageFromMail(email, password);
        messageSubject.shouldHave(Condition.matchesText("Password Reset Request for site"));
        resetPasswordLink.click();
        switchTo().window(2);
        return page(PasswordResetPage.class);

    }
}
