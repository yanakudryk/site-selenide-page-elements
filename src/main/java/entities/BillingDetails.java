package entities;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
@Data
public class BillingDetails {

    private final String firstName;
    private final String lastName;
    private final String company;
    private final String address_1;
    private final String address_2;
    private final String city;
    private final String state;
    private final String postcode;
    private final String phone;
    private final String email;
    private final Boolean createAccount;
    private final String password;
    private final String payment;
}
