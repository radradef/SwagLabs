package pages;

import entities.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage{

    @FindBy(className = "product_label")
    public WebElement productHeader;
    public static final By productHeaderBy
            = By.className("product_label");

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutBtn;
    public static final By logoutBtnBy
            = By.id("logout_sidebar_link");

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

    public void logOut(){
        clicks(this.sidebarBtn);
        if(canSee(ProductPage.logoutBtnBy)){
            clicks(this.logoutBtn);
        } else {
            throw new RuntimeException("Logout button not present");
        }
    }

    public Item addRandomItemToCart() {
        List<WebElement> itemList = this.itemList;
        Random random = new Random();
        int randIndex = random.nextInt(itemList.size());
        WebElement item = itemList.get(randIndex);
        String itemName = item
                            .findElement(ProductPage.itemNameBy)
                            .getText();
        float itemPrice
                = Float.parseFloat(item
                                    .findElement(ProductPage.itemPriceBy)
                                    .getText()
                                    .replaceAll("[^\\d.-]", ""));

        clicks(item.findElement(ProductPage.addToCartBtnBy));

        Item addedItem = new Item();
        addedItem.setName(itemName);
        addedItem.setPrice(itemPrice);

        return addedItem;
    }
}
