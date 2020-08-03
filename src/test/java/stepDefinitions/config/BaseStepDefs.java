package stepDefinitions.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseStepDefs {
    protected WebDriver driver;

    public abstract void setUp();
    public abstract void tearDown();

    protected void driverInit(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }
}
