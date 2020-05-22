package oauth2;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class AuthorisationCode {
    @Test
    public void AuthCodeTest() {


        // extract the code from url
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AEzk6F2-SG6xbs21copQDIviENf3GyJWyb9Hl1IV76UWWH8YZKLyrP0WtOZWyqdGHbibjEGhj48uzk5HAON-wI&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";

        String code = url.split("code=")[1].split("&scope=")[0];
        // get access token
        String response = RestAssured
                .given().log().all()
//                    .urlEncodingEnabled(false)
                    .queryParam("code", "K6F2uPbPwAJJVwXVyhhBx6FCm0sUO91zdbZI25NhfyFxRcg_")
                    .queryParam("client_id", "5QcQO7o4rmTdfShPp2VZdnHt")
                    .queryParam("client_secret", "iqF4Osoll9t_jB-egPfqi2f0T4L4traV6TTcT1XU2e-ncY7F")
                    .queryParam("redirect_uri", "https://www.oauth.com/playground/authorization-code.html")
                    .queryParam("grant_type", "authorization_code")
                .when().log().all()
                    .post("https://authorization-server.com/token")
                .then().log().all()
                    .extract().response().asString();

        System.out.println(response);
    }
}
