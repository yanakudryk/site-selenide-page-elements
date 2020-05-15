package productInfoTests;

import baseTests.BaseTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.shopPage.ShopPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.*;

public class ProductTests extends BaseTests {
    protected ShopPage shopPage;

    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = page(ShopPage.class);
    }

    @Test
    public void testProduct(){
        shopPage.
                openProduct(PRODUCT_NAME).
                checkTitle(PRODUCT_NAME).
                checkPrice(String.format(Locale.US,"$%.2f", PRODUCT_PRICE)).
                checkDescription(PRODUCT_DESCRIPTION);
    }
}
