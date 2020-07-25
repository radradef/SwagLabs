package stepDefinitions;

import actions.LoginActions;
import actions.ProductActions;
import entities.Credentials;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.ProductPage;

public class Login {

    private WebDriver driver;
    private LoginPage loginPage;
    private LoginActions loginActions;
    private ProductPage productPage;
    private ProductActions productActions;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginActions = new LoginActions(loginPage);
        productPage = new ProductPage(driver);
        productActions = new ProductActions(productPage);
    }

    @When("^.* goes to the login page$")
    public void goesToTheLoginPage() {
        driver.get(LoginPage.URL);
    }

    @Then("she/he should see the login form")
    public void shouldSeeTheLoginForm() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(loginActions.canSee(LoginPage.usernameFldBy))
                .as("Username field is displayed")
                .isTrue();
        softly.assertThat(loginActions.canSee(LoginPage.passFldBy))
                .as("Password field is displayed")
                .isTrue();
        softly.assertThat(loginActions.canSee(LoginPage.loginBtnBy))
                .as("Login button is displayed")
                .isTrue();

        softly.assertAll();
    }

    @When("^.* logs in with credentials:$")
    public void logsInWithCredentials(Credentials credentials) {
        loginActions.logsInWith(credentials);
    }

    @Then("he/she should see the product page")
    public void shouldSeeTheProductsPage() {
        Assertions
                .assertThat(productActions.canSee(ProductPage.productHeaderBy))
                .as("Product header is visible")
                .isTrue();
    }

    @Then("the product page header should say: {string}")
    public void theProductHeaderShouldSay(String string) {
        Assertions
                .assertThat(productActions.readsFrom(productPage.productHeader))
                .as("product header has the correct text ")
                .isEqualToIgnoringCase(string);
    }

    @Then("he/she should see error message")
    public void shouldSeeErrorMessage() {
        Assertions
                .assertThat(loginActions.canSee(LoginPage.errorBy))
                .as("Error is visible")
                .isTrue();
    }

    @Then("the error message should say:")
    public void theErrorMessageShouldSay(String error) {
        Assertions
                .assertThat(loginActions.readsFrom(loginPage.error))
                .as("Error has the correct text")
                .containsIgnoringCase(error);
    }

    @After
    public void tearDown(){
        driver.close();
    }



}