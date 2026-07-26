package id.seriesgi.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private DriverFactory() { }

    public static void start() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1440,1000", "--disable-dev-shm-usage", "--no-sandbox");
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() { return DRIVER.get(); }
    public static void quit() { if (DRIVER.get() != null) { DRIVER.get().quit(); DRIVER.remove(); } }
}
