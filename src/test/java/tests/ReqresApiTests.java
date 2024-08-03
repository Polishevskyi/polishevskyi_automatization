package tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import lib.BaseApiSpecifications;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ReqresApiTests extends BaseApiSpecifications {

    private static final String BASE_URL = "https://reqres.in/";

    @Test
    public void getListUsersTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/users?page=2")
                .then()
                .body(matchesJsonSchemaInClasspath("get-list-users-schema.json"));
    }

    @Test
    public void getSingleUserTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/users/2")
                .then()
                .body(matchesJsonSchemaInClasspath("get-single-user-schema.json"));
    }

    @Test
    public void getSingleUserNotFoundTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(404));
        given()
                .get("/api/users/23");
    }

    @Test
    public void postCreateUserTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(201));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        given()
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .body(matchesJsonSchemaInClasspath("post-create-user-schema.json"));
    }

    @Test
    public void putUpdateTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");

        given()
                .body(requestBody)
                .when()
                .put("/api/users/2")
                .then()
                .body(matchesJsonSchemaInClasspath("put-update-schema.json"));
    }

    @Test
    public void postLoginSuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");

        given()
                .body(requestBody)
                .when()
                .post("/api/login")
                .then()
                .body(matchesJsonSchemaInClasspath("post-login-successful-schema.json"));
    }

    //test
}
