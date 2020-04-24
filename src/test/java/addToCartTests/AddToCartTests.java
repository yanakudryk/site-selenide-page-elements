package addToCartTests;

import baseTests.BaseTests;
import com.github.javafaker.Faker;
import entities.BillingDetails;
import org.junit.Before;
import org.junit.Test;
import pages.shopPage.Product;
import pages.shopPage.ShopPage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.page;

public class AddToCartTests extends BaseTests {

    protected ShopPage shopPage;


    @Before
    public void createPages(){
        shopPage = page(ShopPage.class);
    }

    @Test
    public void testAddProductToCart (){
        shopPage.addProductToCart("Ship Your Idea").checkAddToCartMessage("Ship Your Idea");
    }
    @Test
    public void testPlaceOrderWithCheckPaymentsPaymentMethod(){
        Faker usFaker = new Faker(new Locale("en-US"));
        BillingDetails billDet =
                new BillingDetails(
                        usFaker.name().firstName(),
                        usFaker.name().lastName(),
                        usFaker.company().name(),
                        usFaker.address().streetAddress(),
                        usFaker.address().streetAddressNumber(),
                        usFaker.address().city(),
                        usFaker.address().state(),
                        usFaker.address().zipCode(),
                        usFaker.phoneNumber().cellPhone(),
                        usFaker.name().username().concat("@email.com"),
                        true,
                        "123asdQQ!!!098",
                        "cheque");
        shopPage.
                addProductToCart("Happy Ninja").
                openCart().
                checkTotalSum("$70.00").
                proceedToCheckOut().
                fillBillingDetails(billDet).
                placeOrder().
                checkPaymentMethod("Check Payments");
    }
}
