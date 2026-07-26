package id.seriesgi.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage {
    private final By name = By.id("name");
    private final By country = By.id("country");
    private final By city = By.id("city");
    private final By card = By.id("card");
    private final By purchase = By.cssSelector("button[onclick='purchaseOrder()']");
    private final By confirmation = By.cssSelector(".sweet-alert.showSweetAlert.visible h2");
    public OrderPage(WebDriver driver) { super(driver); }
    public void checkout(String customerName) {
        type(name, customerName); type(country, "Indonesia"); type(city, "Jakarta"); type(card, "4111111111111111"); click(purchase);
    }
    public String confirmationMessage() { return text(confirmation); }
}
