package id.seriesgi.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String APP_ID = System.getProperty("dummy.api.app.id", "63a804408eb0cb069b57e43a");

    public ApiClient() {
        RestAssured.baseURI = BASE_URL;
    }

    public Response getUserById(String userId) {
        return RestAssured.given().header("app-id", APP_ID).get("/user/{id}", userId);
    }

    public Response getTags() {
        return RestAssured.given().header("app-id", APP_ID).get("/tag");
    }

    public Response createUser(Map<String, String> payload) {
        return RestAssured.given().header("app-id", APP_ID).contentType("application/json").body(payload).post("/user/create");
    }

    public Response updateUser(String userId, Map<String, String> payload) {
        return RestAssured.given().header("app-id", APP_ID).contentType("application/json").body(payload).put("/user/{id}", userId);
    }

    public Response deleteUser(String userId) {
        return RestAssured.given().header("app-id", APP_ID).delete("/user/{id}", userId);
    }
}
