package id.seriesgi.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By firstProduct = By.cssSelector("#tbodyid .card .hrefch");
    public HomePage(WebDriver driver) { super(driver); }
    public HomePage open() { driver.get("https://www.demoblaze.com/"); return this; }
    public ProductPage openFirstProduct() { click(firstProduct); return new ProductPage(driver); }
}
