package actions;

import pages.ProductPage;

public class ProductActions extends BaseActions{
    private ProductPage productPage;

    public ProductActions(ProductPage productPage) {
        super(productPage.getDriver());
        this.productPage = productPage;
    }
}
