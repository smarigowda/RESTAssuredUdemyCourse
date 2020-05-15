import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello and Welcome to REST API Testing using Rest Assured...");
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Add a new place
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(PayLoad.AddPlace())
                .when()
                    .post("/maps/api/place/add/json")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("scope", equalTo("APP"))
                        .header("server", equalTo("Apache/2.4.18 (Ubuntu)"))
                    .extract().response().asString();

        System.out.println("Response is:\n" + response);
        JsonPath jsonResponse = ReusableMethods.rawToJson(response);
        String placeId = jsonResponse.getString("place_id");
        System.out.println(placeId);

        // Update the address of place added in previous step
        String newAddress = "70 Summer walk, USA";
        given().log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when()
                    .put("/maps/api/place/update/json")
                .then().log().all()
                    .assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        // Get the updated place and verify that the address is updated
        String updatedResponse  = given().log().all()
                .queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when()
                    .get("/maps/api/place/get/json")
                .then().log().all()
                    .assertThat().statusCode(200).extract().response().asString();

        JsonPath updatedResponseJson = ReusableMethods.rawToJson(updatedResponse);
        String actualAddress = updatedResponseJson.getString("address");
        Assert.assertEquals(actualAddress, newAddress);
    }
}
