package shoppingTests;

import baseTests.BaseTests;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import entities.BillingDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.shopPage.ShopPage;

import java.util.Locale;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.page;
import static credentials.Constants.*;
import static credentials.PayPalCredentials.PAY_PAL_EMAIL_PERSONAL;
import static credentials.PayPalCredentials.PAY_PAL_PASSWORD_PERSONAL;

public class ShoppingTests extends BaseTests {

    protected ShopPage shopPage;

    @BeforeEach
    public void createPages(){
        goHome();
        shopPage = page(ShopPage.class);
    }

    public static Stream<Arguments> paymentMethodsProvider(){
        return Stream.of(
        Arguments.of("cheque", "Check Payments"),
        Arguments.of("bacs", "Direct bank transfer"),
        Arguments.of("cod", "Cash on delivery"));
    }

    @ParameterizedTest
    @MethodSource("paymentMethodsProvider")
    public void testPlaceOrderWithPaymentsPaymentMethod(String payment, String paymentMethod){
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
                        usFaker.name().username().concat(MAIL_DOMAIN),
                        true,
                        PASSWORD,
                        payment);
        shopPage.
                addProductToCart(PRODUCT_NAME).
                checkAddToCartMessage(PRODUCT_NAME).
                openCart().
                checkTotalSum(String.format(Locale.US,"$%.2f", PRODUCT_PRICE * 2)).
                proceedToCheckOut().
                fillBillingDetails(billDet).
                placeOrder().
                checkPaymentMethod(paymentMethod);
    }
    @Test
    public void testPlaceOrderWithPayPalPaymentsPaymentMethod(){
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
                        usFaker.name().username().concat(MAIL_DOMAIN),
                        true,
                        PASSWORD,
                        "paypal");
        shopPage.
                addProductToCart(PRODUCT_NAME).
                checkAddToCartMessage(PRODUCT_NAME).
                openCart().
                checkTotalSum(String.format(Locale.US,"$%.2f", PRODUCT_PRICE * 2)).
                proceedToCheckOut().
                fillBillingDetails(billDet).
                proceedToPayPal().
                loginToPayPal(PAY_PAL_EMAIL_PERSONAL, PAY_PAL_PASSWORD_PERSONAL).
                clickPayNow().
                checkPaymentMethod("PayPal");
    }
    @Test
    public void testPlaceOrderWithCoupon(){
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
                        usFaker.name().username().concat(MAIL_DOMAIN),
                        true,
                        PASSWORD,
                        PAYMENT_METHOD);
        shopPage.
                addProductToCart(PRODUCT_NAME).
                checkAddToCartMessage(PRODUCT_NAME).
                openCart().
                checkTotalSum(String.format(Locale.US,"$%.2f", PRODUCT_PRICE * 2)).
                applyCoupon(COUPON_CODE).
                checkApplyCouponMessage().
                checkTotalSum(String.format(Locale.US,"$%.2f", PRODUCT_PRICE * 2 - COUPON_DISCOUNT)).
                proceedToCheckOut().
                fillBillingDetails(billDet).
                placeOrder().
                checkTotalSum(String.format(Locale.US,"$%.2f", PRODUCT_PRICE * 2 - COUPON_DISCOUNT));
    }
}
