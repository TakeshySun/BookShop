package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class apiHelper {
    RequestSpecification requestSpecification;

    public static void main(String[] args) throws JsonProcessingException {
        String baseUrl = "https://www.kruidvat.nl";
        String cartUrl = "https://www.kruidvat.nl/cart";
        String pathCart = "/api/v2/kvn/users/anonymous/carts/";

        String guid = new apiHelper().getJsonValue(baseUrl, pathCart, "guid");
        String pathCartGuid = String.format("/api/v2/kvn/users/anonymous/carts/%s/entries", guid);

        new apiHelper().post(baseUrl, pathCartGuid); // Simple RestAssured post
        new apiHelper().addProductObMap(baseUrl, pathCartGuid); // Object Builder post

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(cartUrl);
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(new Cookie("kvn-cart", guid));
        driver.navigate().refresh();

        driver.quit();

    }

    public String getJsonValue(String url, String path, String key) {
        requestSpecification = RestAssured.given()
                .baseUri(url)
                .basePath(path);

        return RestAssured.given()
                .spec(requestSpecification)
                .post()
                .jsonPath()
                .getString(key);
    }

    // Simple RestAssured post
    public void post(String url, String path) {
        requestSpecification = RestAssured.given()
                .baseUri(url)
                .basePath(path)
                .contentType("application/json;charset=UTF-8");
        RestAssured.given()
                .spec(requestSpecification)
                .body("{\"product\": {\"code\": \"2876350\"},\"quantity\": 2}")
                .when()
                .post();
    }

    // Object Builder post
    public void addProductObMap(String url, String path) throws JsonProcessingException {
        ProductCode productCode = new ProductCode();
        productCode.setCode("2876350");
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setQuantity(1);
        productQuantity.setProduct(productCode);

        ObjectMapperUtil om = new ObjectMapperUtil();
        String body = om.writeValueAsString(productQuantity);

        requestSpecification = RestAssured.given()
                .baseUri(url)
                .basePath(path)
                .contentType("application/json;charset=UTF-8");
        RestAssured.given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post();
    }
}
