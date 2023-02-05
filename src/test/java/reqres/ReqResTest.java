package reqres;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reqres.pojo.CreateUserRequestModel;
import reqres.pojo.CreateUserResponseModel;
import reqres.pojo.LoginUserRequestModel;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static reqres.Specs.requestSpec;
import static reqres.Specs.responseSpec;

public class ReqResTest {

    @BeforeAll
    static void beforeClass() {
        requestSpecification = requestSpec();
        responseSpecification = responseSpec();
    }

    @Test
    void verifyQuantityUsersInResponse() {
        int quantityUsersInResponse = 6;
        given()
                .get("/users/")
                .then()
                .body("data",
                        hasSize(quantityUsersInResponse));
    }

    @Test
    void verifyEmailGroovyTest() {
        given()
                .get("/users/")
                .then()
                .body("data.email.flatten()",
                        hasItem("janet.weaver@reqres.in"));
    }

    @Test
    void unsuccessfulLoginUserTest() {
        LoginUserRequestModel body = LoginUserRequestModel.builder()
                .email("eve.holt@reqres.in")
                .password("")
                .build();

        given()
                .body(body)
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createUserTest() {
        CreateUserRequestModel body = CreateUserRequestModel.builder()
                .name("pavel")
                .job("student")
                .build();

        CreateUserResponseModel response =
                given()
                        .body(body)
                        .when()
                        .post("/users")
                        .then()
                        .statusCode(201)
                        .extract().as(CreateUserResponseModel.class);

        assertThat(response.getName()).isEqualTo(body.getName());
        assertThat(response.getJob()).isEqualTo(body.getJob());
        assertThat(response.getId()).isNotNull();
    }

    @Test
    void deleteUserTest() {
        given()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
