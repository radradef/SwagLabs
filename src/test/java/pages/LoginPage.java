package pages;

import entities.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public static final String URL = "https://www.saucedemo.com/";
    private String defaultUser = "standard_user";
    private String getDefaultPass = "secret_sauce";

    @FindBy(id = "user-name")
    public WebElement usernameFld;
    public static final By usernameFldBy
            = By.id("user-name");

    @FindBy(id = "password")
    public WebElement passFld;
    public static final By passFldBy
            = By.id("password");

    @FindBy(id = "login-button")
    public WebElement loginBtn;
    public static final By loginBtnBy
            = By.id("login-button");

    @FindBy(css = "h3[data-test='error']")
    public WebElement error;
    public static final By errorBy
            = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public ProductPage logsInWith(Credentials credentials){
        fills(this.usernameFld,
                credentials.getUsername());
        fills(this.passFld,
                credentials.getPassword());
        clicks(this.loginBtn);
        waitForPageLoad();

        return new ProductPage(driver);
    }

    public ProductPage logsInDefault(){
        fills(this.usernameFld,
                defaultUser);
        fills(this.passFld,
                getDefaultPass);
        clicks(this.loginBtn);
        waitForPageLoad();

        return new ProductPage(driver);
    }
}
