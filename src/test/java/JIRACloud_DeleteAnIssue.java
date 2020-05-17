import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.testng.annotations.Test;

public class JIRACloud_DeleteAnIssue {
    @Test
    public void DeleteAnIssue() {
        RestAssured.baseURI = "https://santosharakere.atlassian.net";

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("santosharakere@gmail.com");
        authScheme.setPassword(System.getenv("JIRA_API_TOKEN"));
        RestAssured.authentication = authScheme;

        RestAssured
                .given()
                    .log().all()
                    .pathParam("issueId", "10002")
                .when()
                    .delete("/rest/api/2/issue/{issueId}")
                .then()
                    .log().all()
                    .statusCode(204);
    }
}
