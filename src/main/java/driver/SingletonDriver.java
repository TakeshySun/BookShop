package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static constants.Constants.IMPLICITLY_WAIT_TIMEOUT;
import static driver.CapabilitiesHelper.getChromeOptions;
import static driver.CapabilitiesHelper.getFirefoxOptions;

public class SingletonDriver {

    private static final ThreadLocal<WebDriver> instance = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return instance.get();
    }

    public static WebDriver createDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                instance.set(new ChromeDriver());
                instance.get().manage().deleteAllCookies();
                instance.get().manage().window().maximize();
                instance.get().manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
                return instance.get();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                instance.set(new FirefoxDriver(getFirefoxOptions()));
                instance.get().manage().deleteAllCookies();
                instance.get().manage().window().maximize();
                instance.get().manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
                return instance.get();
            default:
                throw new IllegalStateException("This driver is not supported");
        }
    }


    public static void quit() {
        instance.get().quit();
        instance.remove();
    }
}
