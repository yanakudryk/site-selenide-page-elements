package pages.shopPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class Category extends ElementsContainer {

    @FindBy(how = How.XPATH, using = "a")
    protected SelenideElement categoryName;
    @FindBy(how = How.XPATH, using = "span")
    protected SelenideElement categoryCount;

}
