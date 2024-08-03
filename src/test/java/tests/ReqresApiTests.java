package tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import lib.BaseApiSpecifications;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ReqresApiTests extends BaseApiSpecifications {

    private final String BASE_URL = "https://reqres.in/";

    @Test(priority = 1)
    public void getListUsersTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/users?page=2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-list-users-schema.json"));
    }

    @Test(priority = 2)
    public void getSingleUserTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/users/2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-single-user-schema.json"));
    }

    @Test(priority = 3)
    public void getSingleUserNotFoundTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(404));
        given()
                .get("/api/users/23");
    }

    @Test(priority = 4)
    public void getListResourceTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/unknown")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-list-resource-schema.json"));
    }

    @Test(priority = 5)
    public void getSingleResourceTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/unknown/2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-single-resource-schema.json"));
    }

    @Test(priority = 6)
    public void getSingleResourceNotFoundTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(404));
        given()
                .get("/api/unknown/23");
    }

    @Test(priority = 7)
    public void postCreateTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(201));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        given()
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-create-user-schema.json"));
    }

    @Test(priority = 8)
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
                .body(matchesJsonSchemaInClasspath("reqres_schemes/put-update-schema.json"));
    }

    @Test(priority = 9)
    public void patchUpdateTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");

        given()
                .body(requestBody)
                .when()
                .patch("/api/users/2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/put-update-schema.json"));
    }

    @Test(priority = 10)
    public void deleteDeleteTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(204));
        given()
                .delete("/api/users/2");
    }

    @Test(priority = 11)
    public void postRegisterSuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");

        given()
                .body(requestBody)
                .when()
                .post("/api/register")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-register-successful-schema.json"));
    }

    @Test(priority = 12)
    public void postRegisterUnsuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "sydney@fife");

        given()
                .body(requestBody)
                .when()
                .post("/api/register")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-register-unsuccessful-schema.json"));
    }

    @Test(priority = 13)
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
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-login-successful-schema.json"));
    }

    @Test(priority = 14)
    public void postLoginUnsuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "peter@klaven");

        given()
                .body(requestBody)
                .when()
                .post("/api/login")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-login-unsuccessful-schema.json"));
    }

    @Test(priority = 15)
    public void getDelayedResponseTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/users?delay=3")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-delayed-response-schema.json"));
    }
}
