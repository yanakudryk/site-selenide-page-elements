package productCategiesTests;

import baseTests.BaseTests;
import org.junit.Before;
import org.junit.Test;
import pages.shopPage.ShopPage;

import static com.codeborne.selenide.Selenide.page;

public class ProductCategoriesTests extends BaseTests {

    protected ShopPage shopPage;

    @Before
    public void createPages(){
        shopPage = page(ShopPage.class);
    }

    @Test
    public void testSelectProductCategory(){
        shopPage.selectCategory("Clothing").
                checkShopCategory("Clothing").
                checkProductsCategories("Clothing");
    }
}
