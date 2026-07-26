package id.seriesgi.web.steps;

import id.seriesgi.web.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class WebHooks {
    @Before("@web") public void startBrowser() { DriverFactory.start(); }
    @After("@web") public void closeBrowser() { DriverFactory.quit(); }
}
