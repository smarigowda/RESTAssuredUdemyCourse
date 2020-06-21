package specbuilders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecBuildersDemo {
    public static void main(String[] args) {
        System.out.println("Demo of spec builders.....");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                        .setBaseUri("https://rahulshettyacademy.com")
                        .addQueryParam("key", "qaclick123")
                        .setContentType(ContentType.JSON)
                        .build();


    }
}
