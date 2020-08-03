package stepDefinitions;

import entities.Credentials;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import pages.LoginPage;
import pages.ProductPage;
import stepDefinitions.config.BaseStepDefs;

public class Login extends BaseStepDefs {

    private LoginPage loginPage;
    private ProductPage productPage;

    @Before("@loginFeature")
    public void setUp(){
        driverInit();
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage = new LoginPage(driver);
        driver.get(LoginPage.URL);
    }

    //@Given
    @When("^.* (?:logs|is logged) in with credentials:$")
    public void logsInWithCredentials(Credentials credentials) {
        productPage = loginPage.logsInWith(credentials);
    }

    @When("he/she logs out")
    public void logOut() {
        productPage.logOut();
    }

    @Then("she/he should see the login form")
    public void shouldSeeTheLoginForm() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(loginPage.canSee(LoginPage.usernameFldBy))
                .as("Username field is displayed")
                .isTrue();
        softly.assertThat(loginPage.canSee(LoginPage.passFldBy))
                .as("Password field is displayed")
                .isTrue();
        softly.assertThat(loginPage.canSee(LoginPage.loginBtnBy))
                .as("Login button is displayed")
                .isTrue();

        softly.assertAll();
    }

    @Then("he/she should see the product page")
    public void shouldSeeTheProductsPage() {
        Assertions
                .assertThat(productPage.canSee(ProductPage.productHeaderBy))
                .as("Product header is visible")
                .isTrue();
    }

    @Then("the product page header should say: {string}")
    public void theProductHeaderShouldSay(String string) {
        Assertions
                .assertThat(productPage.readsFrom(productPage.productHeader))
                .as("product header has the correct text ")
                .isEqualToIgnoringCase(string);
    }

    @Then("he/she should see error message")
    public void shouldSeeErrorMessage() {
        Assertions
                .assertThat(loginPage.canSee(LoginPage.errorBy))
                .as("Error is visible")
                .isTrue();
    }

    @Then("the error message should say:")
    public void theErrorMessageShouldSay(String error) {
        Assertions
                .assertThat(loginPage.readsFrom(loginPage.error))
                .as("Error has the correct text")
                .containsIgnoringCase(error);
    }

    @After("@loginFeature")
    public void tearDown(){
        driver.close();
    }

}
