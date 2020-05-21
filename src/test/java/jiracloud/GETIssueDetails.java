package jiracloud;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GETIssueDetails {
    @Test
    public void getIssueDetails() {
        RestAssured.baseURI = "https://santosharakere.atlassian.net";

        var authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("santosharakere@gmail.com");
        authScheme.setPassword(System.getenv("JIRA_API_TOKEN"));
        RestAssured.authentication = authScheme;

        var expAttachmentId = "10000";
        var expCommentId = "10003";

        var response = RestAssured
                .given()
                    .relaxedHTTPSValidation()
                    .pathParam("issueId", "10000")
                    .queryParam("fields", "comment")
                .when()
                    .get("/rest/api/3/issue/{issueId}")
                .then()
                    .assertThat().statusCode(200)
                    .extract().response().asString();

        System.out.println(response);

        var js = new JsonPath(response);
        int count = js.getInt("fields.comment.comments.size()");
        System.out.println(count);
        for(int i=0; i<count; i++) {
            var commentId = js.getString(String.format("fields.comment.comments[%d].id", i));
            System.out.println(commentId);
            if(commentId.equals(expCommentId)) {
                var commentText = js.getString(String.format("fields.comment.comments[%d].body.content[0].content[0].text", i));
                System.out.println(commentText);
                Assert.assertEquals(commentText, "Comment updated using REST API, using RestAssured framework");
            }
        }
    }
}
