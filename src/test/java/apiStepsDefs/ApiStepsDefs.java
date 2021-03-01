package apiStepsDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ApiHelper;

import java.util.concurrent.TimeUnit;

import static constants.Constants.IMPLICITLY_WAIT_TIMEOUT;
import static driver.CapabilitiesHelper.getChromeOptions;

public class ApiStepsDefs {

    ApiHelper apiHelper = new ApiHelper();
    String baseUrl = "https://www.kruidvat.nl";

    @Given("create a cart")
    public void createACart(){
        apiHelper.createCart(baseUrl);
    }

    @And("add product in cart")
    public void addProductInCart() throws Exception {
        apiHelper.addProduct("src/main/resources/templateApi", baseUrl);
    }

    @And("open cart in web")
    public void openCartInWeb() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver(getChromeOptions());
        driver.get("https://www.kruidvat.nl/cart");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().addCookie(new Cookie("kvn-cart", apiHelper.storage.get("guid").toString()));
        driver.navigate().refresh();

        driver.quit();
    }
}
