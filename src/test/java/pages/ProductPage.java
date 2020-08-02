package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage{

    @FindBy(className = "product_label")
    public WebElement productHeader;
    public static final By productHeaderBy
            = By.className("product_label");

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutBtn;

    @FindBy(xpath = "//button[contains(text(), 'Open Menu')]")
    public WebElement sidebarBtn;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
}
