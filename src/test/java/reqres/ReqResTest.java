package reqres;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqResTest {

    @BeforeAll
    static void beforeClass() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void getUserTest() {
        given()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body(
                        "data.id", is(2),
                        "data.email", is("janet.weaver@reqres.in")
                );
    }

    @Test
    void successfulLoginUserTest() {
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    void unsuccessfulLoginUserTest() {
        String data = "{ \"email\": \"eve.holt@reqres.in\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createUserTest() {
        String data = "{ \"name\": \"pavel\", \"job\": \"student\" }";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body(
                        "name", is("pavel"),
                        "job", is("student"),
                        "id", notNullValue()
                );
    }

    @Test
    void deleteUserTest() {
        given()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }
}
