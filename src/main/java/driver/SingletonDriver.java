package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static constants.Constants.IMPLICITLY_WAIT_TIMEOUT;
import static driver.CapabilitiesHelper.getChromeOptions;
import static driver.CapabilitiesHelper.getFirefoxOptions;

public class SingletonDriver {

    private static WebDriver instance;

//    public static WebDriver getDriver() {
//        if (instance == null) {
//            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//            instance = new ChromeDriver(getChromeOptions());
//            instance.manage().deleteAllCookies();
//            instance.manage().window().maximize();
//            instance.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
//
//        }
//        return instance;
//    }

    public static WebDriver getDriver() {
        if (instance == null) {
            instance.manage().deleteAllCookies();
            instance.manage().window().maximize();
            instance.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        }

        return instance;
    }

    public static WebDriver createDriver(String browser) {

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                instance = new ChromeDriver(getChromeOptions());
                instance.manage().deleteAllCookies();
                instance.manage().window().maximize();
                instance.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
                return instance;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                instance = new FirefoxDriver(getFirefoxOptions());
                instance.manage().deleteAllCookies();
                instance.manage().window().maximize();
                instance.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
                return instance;
            default:
                throw new IllegalStateException("This driver is not supported");
        }
    }


    public static void quit() {
        instance.quit();
        instance = null; // we destroy the driver object after quit operation
    }
}
