package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseActions {

    private WebDriver driver;

    protected BaseActions(WebDriver driver){
        this.driver = driver;
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

    protected void fills(WebElement element, String data){
        element.clear();
        element.sendKeys(data);
    }

    protected void clicks(WebElement element){
        element.click();
    }

    public String readsFrom(WebElement element){
        return element.getText();
    }
}
