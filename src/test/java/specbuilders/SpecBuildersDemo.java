package specbuilders;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuildersDemo {
    public static void main(String[] args) {
        System.out.println("Demo of spec builders.....");

        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();

        requestBuilder.setBaseUri("https://rahulshettyacademy.com")
                      .addQueryParam("key", "qaclick123")
                      .setContentType(ContentType.JSON);

        RequestSpecification requestSpec = requestBuilder.build();

         ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder()
                                                        .expectStatusCode(200)
                                                        .expectContentType(ContentType.JSON);

        ResponseSpecification responseSpec = responseBuilder.build();

        requestSpec = requestSpec
                        .body("{\n" +
                                "  \"location\": {\n" +
                                "    \"lat\": -38.383494,\n" +
                                "    \"lng\": 33.427362\n" +
                                "  },\n" +
                                "  \"accuracy\": 50,\n" +
                                "  \"name\": \"Frontline house\",\n" +
                                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                                "  \"address\": \"29, side layout, cohen 09\",\n" +
                                "  \"types\": [\"shoe park\", \"shop\"],\n" +
                                "  \"website\": \"http://google.com\",\n" +
                                "  \"language\": \"French-IN\"\n" +
                                "}");

        String response = RestAssured.given()
                                            .spec(requestSpec)
                                        .when()
                                            .post("/maps/api/place/add/json")
                                        .then()
                                            .spec(responseSpec)
                                            .extract().response().asString();

        System.out.println(response);
    }
}
