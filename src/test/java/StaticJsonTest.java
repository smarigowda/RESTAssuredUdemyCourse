import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class StaticJsonTest {
    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String filePath = "/Users/santosh/IdeaProjects/SampleMavenProject/static-assets/addbook.json";

        // Add a book
        String addResponse = RestAssured
                                .given()
                                    .header("Content-Type", "application/json")
                                    .body(ReusableMethods.GenerateStringFromResource(filePath))
                                .when()
                                    .post("/Library/Addbook.php")
                                .then()
                                    .assertThat().statusCode(200)
                                    .extract().response().asString();

        System.out.println(addResponse);
    }
}