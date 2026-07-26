package id.seriesgi.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By placeOrder = By.cssSelector("button[data-target='#orderModal']");
    public CartPage(WebDriver driver) { super(driver); }
    public OrderPage placeOrder() { click(placeOrder); return new OrderPage(driver); }
}
