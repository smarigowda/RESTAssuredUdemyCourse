import files.PayLoad;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class DynamicJsonTest {
    @Test
    public void addBook() {
        RestAssured.baseURI="http://216.10.245.166";

        RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .body(PayLoad.AddBook())
                .when()
                    .post("/Library/Addbook.php")
                .then().log().all()
                    .statusCode(200);
    }
}
