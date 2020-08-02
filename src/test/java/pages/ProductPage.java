package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends BasePage{

    @FindBy(className = "product_label")
    public WebElement productHeader;
    public static final By productHeaderBy
            = By.className("product_label");

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutBtn;

    @FindBy(xpath = "//button[contains(text(), 'Open Menu')]")
    public WebElement sidebarBtn;

    @FindBy(className = "inventory_item")
    public List<WebElement> itemList;

    public static final By addToCartBtnBy
            = By.xpath(".//button[(contains(text(),'ADD TO CART'))]");

    public static final By itemNameBy
            = By.xpath(".//div[contains(@class,'inventory_item_name')]");

    public static final By itemPriceBy
            = By.xpath(".//div[contains(@class,'inventory_item_price')]");

    @FindBy(xpath = "//a[contains(@href,'./cart.html')]")
    public WebElement cartLink;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
}
