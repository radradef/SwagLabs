package actions;

import entities.Item;
import org.openqa.selenium.WebElement;
import pages.ProductPage;

import java.util.List;
import java.util.Random;

public class ProductPageActions extends BaseActions{
    private ProductPage productPage;

    public ProductPageActions(ProductPage productPage) {
        super(productPage.getDriver());
        this.productPage = productPage;
    }

    public void logOut(){
        clicks(productPage.sidebarBtn);
        clicks(productPage.logoutBtn);
    }

    public Item addRandomItemToCart() {
        List<WebElement> itemList = productPage.itemList;
        Random random = new Random();
        int randIndex = random.nextInt(itemList.size());
        WebElement item = itemList.get(randIndex);
        String itemName = item.findElement(ProductPage.itemNameBy)
                            .getText();
        float itemPrice = Float.parseFloat(item.findElement(ProductPage.itemPriceBy)
                                            .getText()
                                            .replaceAll("[^\\d.-]", ""));

        clicks(item.findElement(ProductPage.addToCartBtnBy));

        Item addedItem = new Item();
        addedItem.setName(itemName);
        addedItem.setPrice(itemPrice);

        return addedItem;
    }
}
