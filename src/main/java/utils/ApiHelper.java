package utils;

import api.ProductCode;
import api.ProductQuantity;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;


import java.util.HashMap;
import java.util.Map;

public class ApiHelper {
    private RequestSpecification requestSpecification;
    public Map<String, Object> storage = new HashMap<>();

    public void createCart(String url) {
        requestSpecification = RestAssured.given()
                .baseUri(url)
                .basePath("/api/v2/kvn/users/anonymous/carts/");

        Response response = RestAssured.given()
                .spec(requestSpecification)
                .post();
        String guid = response.jsonPath().get("guid");
        storage.put("guid", guid);
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
    public void addProductObMap(String url) throws JsonProcessingException {
        ProductCode productCode = new ProductCode();
        productCode.setCode("2876350");
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setQuantity(1);
        productQuantity.setProduct(productCode);

        requestSpecification = RestAssured.given()
                .baseUri(url)
                .basePath(String.format("/api/v2/kvn/users/anonymous/carts/%s/entries", storage.get("guid").toString()))
                .contentType("application/json;charset=UTF-8");
        RestAssured.given()
                .spec(requestSpecification)
                .body(new ObjectMapperUtil().writeValueAsString(productQuantity))
                .when()
                .post();
    }

    public void addProduct(String filePath, String url) throws Exception {
        Map<String, Object> values = new HashMap<>();
        values.put("code", "2876350");
        values.put("quantity", 5);

        String parsedJson = new ReadJsonAsString().parseJsonTemplate(filePath, values);

        requestSpecification = RestAssured.given()
                .baseUri(url)
                .basePath(getCardGuidEndpoint())
                .contentType("application/json;charset=UTF-8");
        Response response = RestAssured.given()
                .spec(requestSpecification)
                .body(parsedJson)
                .when()
                .post();

//        response.prettyPrint(); // Helper - Print post response body

        //  Validation
        response.then()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("expectedJSONSchema.json"))
                .body("entry.product.code", equalTo("2876350"))
                .body("quantity", equalTo(5));
    }

    private String getCardGuidEndpoint(){
        return String.format("/api/v2/kvn/users/anonymous/carts/%s/entries", storage.get("guid").toString());
    }
}
