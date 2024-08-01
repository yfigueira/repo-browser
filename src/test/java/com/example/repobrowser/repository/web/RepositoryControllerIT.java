package com.example.repobrowser.repository.web;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RepositoryControllerIT {

    private static final String BASE_PATH = "/api/repositories";

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void whenRequestedUsernameIsValid_thenStatus200() {
        given()
                .accept("application/json")
                .when()
                .get("%s?username={username}".formatted(BASE_PATH), Username.valid)
                .then()
                .statusCode(200);
    }

    @Test
    void whenStatusOk_thenReturnsExpectedResponseFormat() {
        given()
                .accept("application/json")
                .when()
                .get("%s?username={username}".formatted(BASE_PATH), Username.valid)
                .then()
                .body("name", notNullValue())
                .body("owner.login", notNullValue())
                .body("branches.name", notNullValue())
                .body("branches.commit.sha", notNullValue());
    }

    @Test
    void whenRequestedUsernameNotFound_thenStatus404() {
        given()
                .accept("application/json")
                .when()
                .get("%s?username={username}".formatted(BASE_PATH), Username.notValid)
                .then()
                .statusCode(404);
    }

    @Test
    void whenStatus404_thenReturnsExpectedErrorResponseFormat() {
        /*
        * expected error response format:
        * {
        *   "status": ${responseCode},
        *   "message": ${whyHasItHappened}
        * }
        * */

        given()
                .accept("application/json")
                .when()
                .get("%s?username={username}".formatted(BASE_PATH), Username.notValid)
                .then()
                .body("status", notNullValue())
                .body("message", notNullValue())
                .body("message", equalTo("Username [ %s ] not found".formatted(Username.notValid)));
    }

    @Test
    void whenRequestedUsernameMissing_thenStatus400() {
        given()
                .accept("application/json")
                .when()
                .get("%s?username={username}".formatted(BASE_PATH), "")
                .then()
                .statusCode(400);
    }

    @Test
    void whenStatus400_thenReturnsExpectedErrorResponseFormat() {
        /*
         * expected error response format:
         * {
         *   "status": ${responseCode},
         *   "message": ${whyHasItHappened}
         * }
         * */

        given()
                .accept("application/json")
                .when()
                .get("%s?username={username}".formatted(BASE_PATH), "")
                .then()
                .body("status", notNullValue())
                .body("message", notNullValue())
                .body("message", equalTo("Username argument missing"));
    }

    private static class Username {
        static final String valid = "yfigueira";
        static final String notValid = "yfigueiraaa";
    }
}