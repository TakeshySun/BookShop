package apiStepsDefs;

import io.cucumber.java.en.*;

import org.junit.Assert;
import org.openqa.selenium.By;
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
        apiHelper.addProduct("src/main/resources/addProductTemplate", baseUrl);
    }

    @Then("open cart in web and verify product")
    public void openCartInWeb() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver(getChromeOptions());
        driver.get("https://www.kruidvat.nl/cart");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().addCookie(new Cookie("kvn-cart", apiHelper.storage.get("guid").toString()));
        driver.navigate().refresh();

        //Web navigation
        driver.findElement(new By.ByCssSelector("#onetrust-accept-btn-handler")).click();
        driver.findElement(new By.ByCssSelector("e2-select-country-dialog > div > div > div > e2-action-button:nth-child(2) > v-root > button > span.button__text")).click();
        //Web verification
        String productName = driver.findElement(new By.ByCssSelector(" div.product-summary__description-name")).getText();
        String productQuantity = driver.findElement(new By.ByCssSelector("span.select__selected-option")).getText();
        Assert.assertEquals("Kruidvat Sensitive Handzeep Navulling", productName);
        Assert.assertEquals("5", productQuantity);

        driver.quit();
    }

}
