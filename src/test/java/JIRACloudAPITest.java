import com.sun.org.apache.regexp.internal.RE;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.Test;

public class JIRACloudAPITest {
    @Test
    public void CreateIssue() {
        RestAssured.baseURI = "http://santosharakere.atlassian.net";

        System.out.println(System.getenv("JIRA_API_TOKEN"));
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("santosharakere@gmail.com");
        authScheme.setPassword(System.getenv("JIRA_API_TOKEN"));
        RestAssured.authentication = authScheme;

        String response1 = RestAssured
                .given()
                .when()
                    .get("/rest/api/2/issue/RES-1")
                .then()
                    .assertThat().statusCode(200)
                    .extract().response().asString();

        System.out.println("Response 1....");
        System.out.println(response1);

        String response2 = RestAssured
                            .given()
                            .when().log().all()
                                .get("/rest/api/2/issue/RES-1")
                            .then()
                                .assertThat().statusCode(200)
                                .extract().response().asString();
        System.out.println("Response 2....");
        System.out.println(response2);

    }
}
