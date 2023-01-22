package demowebshop;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class ApiTest {

    @BeforeAll
    static void beforeClass() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    @Test
    void addVirtualGiftCardToShoppingCart() {
        Map<String, String> data = new HashMap<>();
        data.put("giftcard_2.RecipientName", "Pavel Li");
        data.put("giftcard_2.RecipientEmail", "pavel@yahoo.com");
        data.put("giftcard_2.SenderName", "Sergey Smith");
        data.put("giftcard_2.SenderEmail", "sergey@yahoo.com");
        data.put("giftcard_2.Message", "for+your+birthday");
        data.put("addtocart_2.EnteredQuantity", "2");

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParams(data)
                .post("/addproducttocart/details/2/1")
                .then()
                .statusCode(200)
                .body(
                        "success", is(true),
                        "message", containsString("The product has been added to your"),
                        "updatetopcartsectionhtml", is("(2)")
                );
    }
}
