import com.sun.org.apache.regexp.internal.RE;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.Test;

public class JIRACloudAPITest {
    @Test
    public void CreateIssue() {
        RestAssured.baseURI = "https://santosharakere.atlassian.net";

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("santosharakere@gmail.com");
        authScheme.setPassword(System.getenv("JIRA_API_TOKEN"));
        RestAssured.authentication = authScheme;

        // Create an Issue
        String response = RestAssured
                .given().log().all()
                    .header("Content-Type", "application/json")
                    .body("{\n" +
                            "\t\"fields\": {\n" +
                            "\t\t\"project\": {\n" +
                            "\t\t\t\"key\": \"RES\"\n" +
                            "\t\t},\n" +
                            "\t\t\"summary\": \"my Second Bug\",\n" +
                            "\t\t\"description\": \"A Bug logged using REST API\",\n" +
                            "\t\t\"issuetype\": {\n" +
                            "\t\t\t\"name\": \"Bug\"\n" +
                            "\t\t}\n" +
                            "\t}\n" +
                            "}")
                .when()
                    .post("/rest/api/2/issue")
                .then()
                    .log().all()
                    .extract().response().asString();

        System.out.println(response);
    }
}
