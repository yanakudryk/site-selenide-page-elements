package baseTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.Highlighter;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.addListener;


public class BaseTests {
    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://site.com";
        Configuration.timeout = 100000;
        Configuration.startMaximized = true;
        Configuration.headless = true;
        addListener(new Highlighter());
        goHome();
    }

    @AfterEach
    public void teatDown(){
        clearBrowserCookies();
    }
    public static void goHome(){
        open("/");
    }
}
