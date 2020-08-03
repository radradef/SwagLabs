package pages;

import entities.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    public List<WebElement> cartItemList;

    public static final By itemNameBy
            = By.xpath(".//div[contains(@class,'inventory_item_name')]");

    public static final By itemPriceBy
            = By.xpath(".//div[contains(@class,'inventory_item_price')]");

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public Item getItem(int index){
        WebElement itemInCart = this.cartItemList.get(index);
        Item item = new Item();
        item.setName(itemInCart.findElement(CartPage.itemNameBy)
                .getText());
        item.setPrice(Float.parseFloat(itemInCart.findElement(CartPage.itemPriceBy)
                .getText()));

        return  item;
    }
}
