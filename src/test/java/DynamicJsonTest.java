import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class DynamicJsonTest {
    @Test
    public void addBook() {
        RestAssured.baseURI="http://216.10.245.166";

        String response = RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .body(PayLoad.AddBook())
                .when()
                    .post("/Library/Addbook.php")
                .then().log().all()
                    .assertThat().statusCode(200)
                    .extract().response().asString();
        System.out.println(response);

        JsonPath js = ReusableMethods.rawToJson(response);
        String id = js.getString("ID");
        System.out.println(id);
    }
}
