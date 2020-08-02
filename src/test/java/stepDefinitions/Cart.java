package stepDefinitions;

import actions.CartPageActions;
import actions.LoginPageActions;
import actions.ProductPageActions;
import entities.Item;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import stepDefinitions.config.BaseStepDefs;

public class Cart implements BaseStepDefs {

    private WebDriver driver;
    private ProductPage productPage;
    private ProductPageActions productPageActions;
    private CartPage cartPage;
    private CartPageActions cartPageActions;
    private LoginPage loginPage;
    private LoginPageActions loginPageActions;
    private Item itemAddedToCart;

    @Before("@cartFeature")
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        productPage = new ProductPage(driver);
        productPageActions = new ProductPageActions(productPage);
        cartPage = new CartPage(driver);
        cartPageActions = new CartPageActions(cartPage);
    }

    @Given("the user is on the Product page")
    public void theUserIsOnTheProductPage() {
        driver.get(LoginPage.URL);
        loginPage = new LoginPage(driver);
        loginPageActions = new LoginPageActions(loginPage);
        loginPageActions.logsInDefault();
        productPageActions.waitForPageLoad();
    }

    @When("^.* adds item to (?:his|her) cart$")
    public void addsItemToCart() {
       itemAddedToCart = productPageActions.addRandomItemToCart();
    }

    @When("she/he navigates to the cart page")
    public void navigatesToTheCartPage() {
        productPageActions.clicks(productPage.cartLink);
        cartPageActions.waitForPageLoad();
    }

    @Then("her/his item should appear in her/his cart")
    public void itemShouldAppearInCart() {
        Item itemInCart = cartPageActions.getItem(0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(itemAddedToCart.getName())
                .as("Added item and item in cart names match")
                .isEqualTo(itemInCart.getName());
        softly.assertThat(itemAddedToCart.getPrice())
                .as("Added item and item in cart prices match")
                .isEqualTo(itemInCart.getPrice());

        softly.assertAll();
    }

    @After("@cartFeature")
    public void tearDown() {
        driver.close();
    }

}
