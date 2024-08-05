package tests.Api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lib.BaseApiSpecifications;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ReqresApiTests extends BaseApiSpecifications {

    private final String BASE_URL = "https://reqres.in/api/";

    @Test(priority = 1)
    @Owner("Polishevskyi")
    @Description("Test for retrieving a list of users from the second page")
    public void getListUsersTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("users?page=2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-list-users-schema.json"));
    }

    @Test(priority = 2)
    @Owner("Polishevskyi")
    @Description("Test for retrieving a single user by ID")
    public void getSingleUserTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("users/2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-single-user-schema.json"));
    }

    @Test(priority = 3)
    @Owner("Polishevskyi")
    @Description("Test for retrieving a non-existent user by ID")
    public void getSingleUserNotFoundTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(404));
        given()
                .get("users/23");
    }

    @Test(priority = 4)
    @Owner("Polishevskyi")
    @Description("Test for retrieving a list of resources")
    public void getListResourceTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("unknown")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-list-resource-schema.json"));
    }

    @Test(priority = 5)
    @Owner("Polishevskyi")
    @Description("Test for retrieving a single resource by ID")
    public void getSingleResourceTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("unknown/2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-single-resource-schema.json"));
    }

    @Test(priority = 6)
    @Owner("Polishevskyi")
    @Description("Test for retrieving a non-existent resource by ID")
    public void getSingleResourceNotFoundTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(404));
        given()
                .get("unknown/23");
    }

    @Test(priority = 7)
    @Owner("Polishevskyi")
    @Description("Test for creating a new user")
    public void postCreateTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(201));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        given()
                .body(requestBody)
                .when()
                .post("users")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-create-user-schema.json"));
    }

    @Test(priority = 8)
    @Owner("Polishevskyi")
    @Description("Test for updating an existing user")
    public void putUpdateTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");

        given()
                .body(requestBody)
                .when()
                .put("users/2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/put-update-schema.json"));
    }

    @Test(priority = 9)
    @Owner("Polishevskyi")
    @Description("Test for partially updating an existing user")
    public void patchUpdateTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "zion resident");

        given()
                .body(requestBody)
                .when()
                .patch("users/2")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/put-update-schema.json"));
    }

    @Test(priority = 10)
    @Owner("Polishevskyi")
    @Description("Test for deleting a user")
    public void deleteDeleteTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(204));
        given()
                .delete("users/2");
    }

    @Test(priority = 11)
    @Owner("Polishevskyi")
    @Description("Test for successful user registration")
    public void postRegisterSuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");

        given()
                .body(requestBody)
                .when()
                .post("register")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-register-successful-schema.json"));
    }

    @Test(priority = 12)
    @Owner("Polishevskyi")
    @Description("Test for unsuccessful user registration with missing password")
    public void postRegisterUnsuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "sydney@fife");

        given()
                .body(requestBody)
                .when()
                .post("register")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-register-unsuccessful-schema.json"));
    }

    @Test(priority = 13)
    @Owner("Polishevskyi")
    @Description("Test for successful user login")
    public void postLoginSuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");

        given()
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-login-successful-schema.json"));
    }

    @Test(priority = 14)
    @Owner("Polishevskyi")
    @Description("Test for unsuccessful user login with missing password")
    public void postLoginUnsuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "peter@klaven");

        given()
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/post-login-unsuccessful-schema.json"));
    }

    @Test(priority = 15)
    @Owner("Polishevskyi")
    @Description("Test for retrieving users with a delay in response")
    public void getDelayedResponseTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("users?delay=3")
                .then()
                .body(matchesJsonSchemaInClasspath("reqres_schemes/get-delayed-response-schema.json"));
    }
}
