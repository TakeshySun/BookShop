import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class apiHelper {

    public static void main(String[] args) {
        String url = "https://www.kruidvat.nl";
        String cartUrl = "https://www.kruidvat.nl/cart";
        String postUrl = "/api/v2/kvn/users/anonymous/carts/";

        String guid = new apiHelper().getGUID(url, postUrl);
        String guidPostUrl = String.format("/api/v2/kvn/users/anonymous/carts/%s/entries", guid);

        new apiHelper().post(url, guidPostUrl);


        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(cartUrl);
        driver.manage().addCookie(new Cookie("kvn-cart", guid));
        driver.navigate().refresh();

        driver.quit();


    }


    public String getGUID(String url, String postUrl) {
        RestAssured.baseURI = url;
        Response response = RestAssured.post(postUrl);
//        System.out.println(response.getBody().asString());
//        System.out.println(response.jsonPath().getString("guid"));
        System.out.println(response.getCookies());
        return response.jsonPath().getString("guid");
    }

    public void post(String url, String postUrl) {
        RestAssured.baseURI = url;
        RestAssured.given()
                .contentType("application/json;charset=UTF-8")
                .body("{\"product\": {\"code\": \"2876350\"},\"quantity\": 1}")
                .when()
                .post(postUrl);
    }
}
