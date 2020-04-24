package baseTests;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;

import static com.codeborne.selenide.Selenide.open;

public class BaseTests {

    @BeforeClass
    public static void setUp(){
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://site.com";
        open("/");
    }


}
