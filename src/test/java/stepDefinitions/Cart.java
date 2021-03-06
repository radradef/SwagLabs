package stepDefinitions;

import entities.Item;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import stepDefinitions.config.BaseStepDefs;

public class Cart extends BaseStepDefs {

    private ProductPage productPage;
    private CartPage cartPage;
    private LoginPage loginPage;
    private Item itemAddedToCart;

    @Before("@cartFeature")
    public void setUp(){
        driverInit();
    }

    @Given("the user is on the Product page")
    public void theUserIsOnTheProductPage() {
        driver.get(LoginPage.URL);
        loginPage = new LoginPage(driver);
        productPage = loginPage.logsInDefault();
    }

    @When("^.* adds item to (?:his|her) cart$")
    public void addsItemToCart() {
       itemAddedToCart = productPage.addRandomItemToCart();
    }

    @When("^.* navigates to the cart page$")
    public void navigatesToTheCartPage() {
        productPage.clicks(productPage.cartLink);
        cartPage = new CartPage(driver);
        cartPage.waitForPageLoad();
    }

    @When("he/she removes the item from the cart")
    public void removesTheItemFromTheCart() {
        cartPage.removeItemFromCart(0);
    }

    @Then("her/his item should appear in her/his cart")
    public void itemShouldAppearInCart() {
        Item itemInCart = cartPage.getItem(0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(itemAddedToCart.getName())
                .as("Added item and item in cart names match")
                .isEqualTo(itemInCart.getName());
        softly.assertThat(itemAddedToCart.getPrice())
                .as("Added item and item in cart prices match")
                .isEqualTo(itemInCart.getPrice());

        softly.assertAll();
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        Assertions.assertThat(cartPage.cartItemList.size())
                .as("0 items in the Cart")
                .isEqualTo(0);
    }
    @After("@cartFeature")
    public void tearDown() {
        driver.close();
    }

}
