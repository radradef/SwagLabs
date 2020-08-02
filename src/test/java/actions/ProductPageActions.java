package actions;

import pages.ProductPage;

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
}
