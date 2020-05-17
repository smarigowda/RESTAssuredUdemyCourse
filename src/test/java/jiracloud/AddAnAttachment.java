package jiracloud;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.testng.annotations.Test;

import java.io.File;


public class AddAnAttachment {
    @Test
    public void AddAttachment() {
        RestAssured.baseURI = "https://santosharakere.atlassian.net";

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("santosharakere@gmail.com");
        authScheme.setPassword(System.getenv("JIRA_API_TOKEN"));
        RestAssured.authentication = authScheme;

        RestAssured
                .given()
                    .header("Content-Type", "multipart/form-data")
                    .header("X-Atlassian-Token", "no-check")
                    .pathParam("issueId", "10000")
                    .multiPart(new File("/Users/santosh/SAN/my_photo.jpg"))
                .when()
                    .post("/rest/api/2/issue/{issueId}/attachments")
                .then().log().all()
                    .assertThat().statusCode(200);
    }
}
