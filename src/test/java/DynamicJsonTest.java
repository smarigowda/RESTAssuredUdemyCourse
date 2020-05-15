import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJsonTest {
    @Test(dataProvider = "BooksData")
    public void addBook(String aisle, String isbn) {
        RestAssured.baseURI = "http://216.10.245.166";

        // Add a book
        String addResponse = RestAssured
                            .given()
                                .header("Content-Type", "application/json")
                                .body(PayLoad.AddBook(isbn, aisle))
                            .when()
                                .post("/Library/Addbook.php")
                            .then()
                                .assertThat().statusCode(200)
                                .extract().response().asString();

        System.out.println(addResponse);

        JsonPath js = ReusableMethods.rawToJson(addResponse);
        String id = js.getString("ID");

        // Delete the book added
        String deleteResponse = RestAssured
                .given()
                    .body(PayLoad.DeleteBook(id))
                .when()
                    .post("/Library/DeleteBook.php")
                .then().extract().response().asString();

        System.out.println(deleteResponse);

        js = ReusableMethods.rawToJson(deleteResponse);
        String responseMessage = js.getString("msg");
        Assert.assertEquals(responseMessage, "book is successfully deleted");

    }

    @DataProvider(name="BooksData")
    public Object[][] getData() {
        return new Object[][] {
                { "8767105", "lkjuhytfg" },
                { "8767106", "lkjuhytfg" },
                { "8767107", "lkjuhytfg" },
        };
    }
}