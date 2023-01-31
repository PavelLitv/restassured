package reqres.helpers;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomApiListener {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured getCustomApiListener() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");
        return FILTER;
    }
}
