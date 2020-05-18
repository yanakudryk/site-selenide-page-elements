package pages.myAccountPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import entities.User;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.basePage.BasePage;
import pages.lostPasswordPage.LostPasswordPage;

import static com.codeborne.selenide.Selenide.page;

public class MyAccountPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//form[@class='woocommerce-form woocommerce-form-login login']")
    private Login loginForm;
    @FindBy(how = How.XPATH, using = "//form[@class='woocommerce-form woocommerce-form-register register']")
    private Register registerForm;

    @FindBy(how = How.ID, using = "account_first_name")
    private SelenideElement firstName;
    @FindBy(how = How.ID, using = "account_last_name")
    private SelenideElement lastName;
    @FindBy(how = How.NAME, using = "save_account_details")
    private SelenideElement saveChangesButton;
    @FindBy(how = How.XPATH, using = "//div[@class='woocommerce-message']")
    private SelenideElement message;
    @FindBy(how = How.XPATH, using = "//div[@class='woocommerce-MyAccount-content']/p[1]")
    private SelenideElement myAccountMessage;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Log out')]")
    private SelenideElement logOutLink;
    @FindBy(how = How.XPATH, using = "//div[@class='u-column1 col-1']/h2")
    private SelenideElement loginHeader;

    @FindBy(how = How.XPATH, using = "//div[@id='content']//li[1]")
    private SelenideElement loginErrorMessage;

    public MyAccountPage login(User user, Boolean rememberMe){
        loginForm.username.val(user.getUsername());
        loginForm.password.val(user.getPassword());
        loginForm.rememberMe.setSelected(rememberMe);
        loginForm.loginButton.click();
        return this;
    }

    public LostPasswordPage lostPassword(){
        loginForm.lostPassword.click();
        return new LostPasswordPage();
    }

    public MyAccountPage register(User user){
        registerForm.email.val(user.getUsername());
        registerForm.password.val(user.getPassword());
        registerForm.registerButton.click();
        return this;
    }

    public MyAccountPage finishRegistration(User user){
        this.firstName.val(user.getFirstName());
        this.lastName.val(user.getLastName());
        saveChangesButton.click();
        return this;
    }

    public void checkEditAccountMessage(){
        message.shouldHave(Condition.matchesText("Account details changed successfully."));
    }

    public void checkIncorrectPasswordOnLoginErrorMessage(String email){
        loginErrorMessage.shouldHave(Condition.text(
                "Error: The password you entered for the email address "
                        + email + " is incorrect. Lost your password?"));
    }

    public void checkMyAccountMessage(String name){
        myAccountMessage.shouldHave((Condition.text("Hello "+ name + " (not " + name + "? Log out)")));
    }

    public MyAccountPage logOut(){
        logOutLink.click();
        return this;
    }

    public void checkUserIsNotLoggedIn(){
        loginHeader.shouldHave(Condition.matchesText("Login"));
    }
}
