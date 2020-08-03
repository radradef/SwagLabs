package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver)
                                .executeScript("return document.readyState")
                                .toString()
                                .equals("complete");
                    }
                };

        try {
            Thread.sleep(500);
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(expectation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean canSee(By by){
        WebDriverWait wait
                = new WebDriverWait(driver,4);
        try {
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(by));
        } catch (Exception e){
            return false;
        }

        return true;
    }

    public void fills(WebElement element, String data){
        element.clear();
        element.sendKeys(data);
    }

    public void clicks(WebElement element){
        element.click();
    }

    public String readsFrom(WebElement element){
        return element.getText();
    }
}

