package productCategiesAndSearchTests;

import baseTests.BaseTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.shopPage.ShopPage;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.PRODUCT_NAME;

public class SearchTests extends BaseTests {
    protected ShopPage shopPage;

    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = page(ShopPage.class);
    }

    @Test
    public void testSearch(){
        shopPage
                .searchProduct(PRODUCT_NAME)
                .checkSearchResults(PRODUCT_NAME);
    }
}
