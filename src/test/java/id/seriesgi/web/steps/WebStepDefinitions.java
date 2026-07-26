package id.seriesgi.web.steps;

import id.seriesgi.web.DriverFactory;
import id.seriesgi.web.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class WebStepDefinitions {
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private OrderPage orderPage;

    @Given("I open the Demoblaze store") public void openStore() { homePage = new HomePage(DriverFactory.getDriver()).open(); }
    @When("I add the first available product to the cart") public void addProduct() { productPage = homePage.openFirstProduct(); cartPage = productPage.addProductAndOpenCart(); }
    @When("I complete checkout with customer name {string}") public void completeCheckout(String name) { orderPage = cartPage.placeOrder(); orderPage.checkout(name); }
    @Then("the order should be placed successfully") public void verifyOrder() { assertThat(orderPage.confirmationMessage(), containsString("Thank you for your purchase")); }
}
