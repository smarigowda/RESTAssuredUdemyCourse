package jiracloud;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.testng.annotations.Test;

public class AddAComment {
    @Test
    public void AddAComment() {
        RestAssured.baseURI = "https://santosharakere.atlassian.net";

        var authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("santosharakere@gmail.com");
        authScheme.setPassword(System.getenv("JIRA_API_TOKEN"));
        RestAssured.authentication = authScheme;

        RestAssured
                .given().log().all()
                    .header("Content-Type", "application/json")
                    .pathParam("issueId", "10000")
                    .body("{\n" +
                            "  \"body\": \"Comment 2: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.\"\n" +
                            "}")
                .when().log().all()
                    .post("/rest/api/2/issue/{issueId}/comment")
                .then().log().all()
                    .statusCode(201);
    }
}
