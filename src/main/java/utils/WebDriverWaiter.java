package utils;

import org.openqa.selenium.support.ui.WebDriverWait;

import static driver.SingletonDriver.getDriver;

public class WebDriverWaiter {

    protected WebDriverWait wait;

    public WebDriverWaiter() {
        wait = new WebDriverWait(getDriver(), 5);
    }
}
