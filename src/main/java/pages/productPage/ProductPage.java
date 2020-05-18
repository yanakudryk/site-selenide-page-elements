package pages.productPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.basePage.BasePage;
import pages.wishListPage.WishListPage;

import static com.codeborne.selenide.Selenide.page;

public class ProductPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//h1[@class='product_title entry-title']")
    private SelenideElement title;
    @FindBy(how = How.XPATH, using = "//p[@class='price']//span[@class='woocommerce-Price-amount amount']")
    private SelenideElement price;

    @FindBy(how = How.XPATH, using = "//div[@class='woocommerce-product-details__short-description']")
    private SelenideElement description;

    @FindBy(how = How.XPATH, using = "(//span[@class='tinvwl_add_to_wishlist-text'])[1]")
    private SelenideElement addToWishList;

    @FindBy(how = How.XPATH, using = "(//span[@class='tinvwl_remove_from_wishlist-text'])[1]")
    private SelenideElement removeFromWishList;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'tinv-txt')]")
    private SelenideElement wishListMessage;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Reviews')]")
    private SelenideElement reviews;

    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'star-')]")
    private ElementsCollection stars;

    @FindBy(how = How.XPATH, using = "//textarea[@id='comment']")
    private SelenideElement comment;

    @FindBy(how = How.XPATH, using = "//input[@id='submit']")
    private SelenideElement submitButton;

    @FindBy(how = How.XPATH, using = "//div[@class='description']")
    private SelenideElement reviewDescription;

    @FindBy(how = How.XPATH, using = "//div[@aria-label='Rated 1 out of 5']")
    private SelenideElement oneStar;
    @FindBy(how = How.XPATH, using = "//div[@aria-label='Rated 2 out of 5']")
    private SelenideElement twoStars;
    @FindBy(how = How.XPATH, using = "//div[@aria-label='Rated 3 out of 5']")
    private SelenideElement threeStars;
    @FindBy(how = How.XPATH, using = "//div[@aria-label='Rated 4 out of 5']")
    private SelenideElement fourStars;
    @FindBy(how = How.XPATH, using = "//div[@aria-label='Rated 5 out of 5']")
    private SelenideElement fiveStars;

    public ProductPage checkTitle(String title){
        this.title.shouldHave(Condition.text(title));
        return this;
    }

    public ProductPage checkPrice(String price){
        this.price.shouldHave(Condition.text(price));
        return this;
    }

    public ProductPage checkDescription(String description){
        this.description.shouldHave(Condition.text(description));
        return this;
    }

    public ProductPage addToWishList(){
        addToWishList.click();
        return this;
    }

    public ProductPage removeFromWishList(){
        removeFromWishList.click();
        return this;
    }

    public ProductPage checkThatProductIsRemovedFromWishList(){
        wishListMessage.shouldHave(Condition.text("Product removed from Wishlist"));
        return this;
    }

    public WishListPage checkThatProductIsAddedToWishList(String name){
        wishListMessage.shouldHave(Condition.text("\""+ name + "\" added to Wishlist"));
        //redirect...to Wish List page
        return new WishListPage();

    }
    public ProductPage goToReviews(){
        reviews.click();
        return this;
    }

    public ProductPage addReview(int amountOfStars, String comment){
        stars.get(amountOfStars).click();
        this.comment.val(comment);
        submitButton.click();
        return this;
    }

    public ProductPage checkReview(int amountOfStars, String comment){
        switch (amountOfStars){
            case 1: oneStar.isDisplayed();
                    break;
            case 2: twoStars.isDisplayed();
                    break;
            case 3: threeStars.isDisplayed();
                    break;
            case 4: fourStars.isDisplayed();
                    break;
            case 5: fiveStars.isDisplayed();
                    break;
        }
        reviewDescription.shouldHave(Condition.text(comment));
        return this;
    }

}
