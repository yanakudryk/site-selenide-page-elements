package productCategiesTests;

import baseTests.BaseTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.shopPage.ShopPage;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.PRODUCT_CATEGORY;

public class ProductCategoriesTests extends BaseTests {

    protected ShopPage shopPage;

    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = page(ShopPage.class);
    }

    @Test
    public void testSelectProductCategory(){
        shopPage.selectCategory(PRODUCT_CATEGORY).
                checkShopCategory(PRODUCT_CATEGORY).
                checkProductsCategories(PRODUCT_CATEGORY);
    }
}
