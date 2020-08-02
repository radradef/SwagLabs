package actions;

import entities.Item;
import org.openqa.selenium.WebElement;
import pages.CartPage;

public class CartPageActions  extends BaseActions{

    private CartPage cartPage;

    public CartPageActions(CartPage cartPage) {
        super(cartPage.getDriver());
        this.cartPage = cartPage;
    }

    public Item getItem(int index){
        WebElement itemInCart = cartPage.cartItemList.get(index);
        Item item = new Item();
        item.setName(itemInCart.findElement(CartPage.itemNameBy)
                        .getText());
        item.setPrice(Float.parseFloat(itemInCart.findElement(CartPage.itemPriceBy)
                        .getText()));

        return  item;
    }
}
