import files.PayLoad;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello and Welcome to REST API Testing using Rest Assured...");
        RestAssured.baseURI = "https://rahulshettyacademy.com";
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
        System.out.println("Response Extracted is:\n" + response);
    }
}
