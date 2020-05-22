package oauth2;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ClientCredentials {
    @Test
    public void CCTest() {
        RestAssured
                .given().log().all()
                    .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                    .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                    .queryParam("grant_type", "client_credentials")
                .when().log().all()
                    .post("https://www.googleapis.com/oauth2/v4/token")
                .then().log().all()
                    .extract().response().asString();
    }
}
