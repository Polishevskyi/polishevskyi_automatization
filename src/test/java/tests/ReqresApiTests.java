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
    public void getListResourceTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/unknown")
                .then()
                .body(matchesJsonSchemaInClasspath("get-list-resource-schema.json"));
    }

    @Test
    public void getSingleResourceTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/unknown/2")
                .then()
                .body(matchesJsonSchemaInClasspath("get-single-resource-schema.json"));
    }

    @Test
    public void getSingleResourceNotFoundTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(404));
        given()
                .get("/api/unknown/23");
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
                .body(matchesJsonSchemaInClasspath("put-update-schema.json"));
    }

    @Test
    public void deleteDeleteTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(204));
        given()
                .delete("/api/users/2");
    }

    @Test
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
                .body(matchesJsonSchemaInClasspath("post-register-successful-schema.json"));
    }

    @Test
    public void postRegisterUnsuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "sydney@fife");

        given()
                .body(requestBody)
                .when()
                .post("/api/register")
                .then()
                .body(matchesJsonSchemaInClasspath("post-register-unsuccessful-schema.json"));
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

    @Test
    public void postLoginUnsuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "peter@klaven");

        given()
                .body(requestBody)
                .when()
                .post("/api/login")
                .then()
                .body(matchesJsonSchemaInClasspath("post-login-unsuccessful-schema.json"));
    }

    @Test
    public void getDelayedResponseTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("/api/users?delay=3")
                .then()
                .body(matchesJsonSchemaInClasspath("get-delayed-response-schema.json"));
    }
}
