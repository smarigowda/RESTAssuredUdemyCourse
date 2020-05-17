import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.testng.annotations.Test;

public class UpdateAComment {
    @Test
    public void UpdateAComment() {
        RestAssured.baseURI = "https://santosharakere.atlassian.net";

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("santosharakere@gmail.com");
        authScheme.setPassword(System.getenv("JIRA_API_TOKEN"));
        RestAssured.authentication = authScheme;

        RestAssured
                .given()
                    .header("Content-Type", "application/json")
                    .pathParam("issueId", "10000")
                    .pathParam("commentId", "10003")
                    .body("{\n" +
                        "  \"body\": \"Comment updated using REST API, using RestAssured framework\"\n" +
                        "}")
                .when().put("/rest/api/2/issue/{issueId}/comment/{commentId}")
                .then().log().all()
                    .statusCode(200);

    }
}