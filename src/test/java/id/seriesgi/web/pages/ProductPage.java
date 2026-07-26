package id.seriesgi.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private final By addToCart = By.cssSelector("a.btn.btn-success.btn-lg");
    private final By cartLink = By.id("cartur");
    public ProductPage(WebDriver driver) { super(driver); }
    public CartPage addProductAndOpenCart() {
        click(addToCart);
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        click(cartLink);
        return new CartPage(driver);
    }
}
