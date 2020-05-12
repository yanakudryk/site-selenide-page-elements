package pages.myAccountPage;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Login extends ElementsContainer {
    @FindBy(how = How.XPATH, using = "p//input[@id='username']")
    protected SelenideElement username;
    @FindBy(how = How.XPATH, using = "p//input[@id='password']")
    protected SelenideElement password;
    @FindBy(how = How.XPATH, using = "p//button[@name='login']")
    protected SelenideElement loginButton;
    @FindBy(how = How.XPATH, using = "p//input[@id='rememberme']")
    protected SelenideElement rememberMe;
    @FindBy(how = How.XPATH, using = "p//a[contains(text(),'Lost your password?')]")
    protected SelenideElement lostPassword;
}
