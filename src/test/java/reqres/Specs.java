package reqres;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;
import static reqres.helpers.CustomApiListener.getCustomApiListener;

public class Specs {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setBasePath("/api")
                .log(URI)
                .log(HEADERS)
                .log(BODY)
                .addFilter(getCustomApiListener())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .build();
    }
}
