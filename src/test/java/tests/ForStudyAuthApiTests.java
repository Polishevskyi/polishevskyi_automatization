package tests;

import lib.BaseApiSpecifications;
import lib.DataGenerator;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ForStudyAuthApiTests extends BaseApiSpecifications {

    private final String BASE_URL = "https://qauto.forstudy.space/api/auth/";

    String email = DataGenerator.getEmail();
    String password = DataGenerator.getPassword();

    @Test(priority = 1)
    public void getAuthLogoutTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        given()
                .get("logOut")
                .then()
                .body(matchesJsonSchemaInClasspath("for_study_auth_schemes/get-auth-logout-schema.json"));
    }

    @Test(priority = 2)
    public void postAuthSignUpSuccessTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(201));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", DataGenerator.getFirstName());
        requestBody.put("lastName", DataGenerator.getLastName());
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("repeatPassword", password);

        given()
                .body(requestBody)
                .when()
                .post("signUp")
                .then()
                .body(matchesJsonSchemaInClasspath("for_study_auth_schemes/post-auth-signup-success-schema.json"));
    }

    @Test(priority = 3)
    public void postAuthSignUpFailTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "John");
        requestBody.put("lastName", "Dou");
        requestBody.put("email", "test@test.com");
        requestBody.put("password", "Qwerty12345");
        requestBody.put("repeatPassword", "Qwerty12345");

        given()
                .body(requestBody)
                .when()
                .post("signUp")
                .then()
                .body(matchesJsonSchemaInClasspath("for_study_auth_schemes/post-auth-signup-fail-schema.json"));
    }

    @Test(priority = 4)
    public void postAuthSignInSuccessTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("remember", "false");

        given()
                .body(requestBody)
                .when()
                .post("signIn")
                .then()
                .body(matchesJsonSchemaInClasspath("for_study_auth_schemes/post-auth-signin-success-schema.json"));
    }

    @Test(priority = 5)
    public void postAuthSignInFailTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "test@test.com");
        requestBody.put("password", "Qwerty12345");
        requestBody.put("remember", "false");

        given()
                .body(requestBody)
                .when()
                .post("signIn")
                .then()
                .body(matchesJsonSchemaInClasspath("for_study_auth_schemes/post-auth-signin-fail-schema.json"));
    }

    @Test(priority = 6)
    public void postAuthResetPasswordSuccessTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);

        given()
                .body(requestBody)
                .when()
                .post("resetPassword")
                .then()
                .body(matchesJsonSchemaInClasspath("for_study_auth_schemes/post-auth-reset-password-success-schema.json"));
    }

    @Test(priority = 7)
    public void postAuthResetPasswordFailTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "test@test");

        given()
                .body(requestBody)
                .when()
                .post("resetPassword")
                .then()
                .body(matchesJsonSchemaInClasspath("for_study_auth_schemes/post-auth-reset-password-fail-schema.json"));
    }
}
