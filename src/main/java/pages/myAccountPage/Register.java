package pages.myAccountPage;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Register extends ElementsContainer {
    @FindBy(how = How.XPATH, using = "p//input[@id='reg_email']")
    protected SelenideElement email;
    @FindBy(how = How.XPATH, using = "p//input[@id='reg_password']")
    protected SelenideElement password;
    @FindBy(how = How.XPATH, using = "p//button[@name='register']")
    protected SelenideElement registerButton;

}
