package utils;

import api.ProductCode;
import api.ProductQuantity;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
        String code = response.jsonPath().get("code");
        String quantity = response.jsonPath().get("quantity");
        storage.put("guid", guid);
        storage.put("quantity", quantity);
        storage.put("code", code);
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
        values.put("quantity", 1);

        String parsedJson = new ReadJsonAsString().parseJsonTemplate(filePath, values);

        requestSpecification = RestAssured.given()
                .baseUri(url)
                .basePath(String.format("/api/v2/kvn/users/anonymous/carts/%s/entries", storage.get("guid").toString()))
                .contentType("application/json;charset=UTF-8");
        RestAssured.given()
                .spec(requestSpecification)
                .body(parsedJson)
                .when()
                .post();
    }

}
