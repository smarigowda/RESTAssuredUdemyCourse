import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello and Welcome to REST API Testing using Rest Assured...");
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given()
                .queryParam("key", "qaclick123")
                .header("ContentType", "application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")
                .when()
                    .post("/maps/api/place/add/json")
                .then()
                    .assertThat().statusCode(200);
    }
}
