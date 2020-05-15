package files;

import io.restassured.path.json.JsonPath;

import java.util.Arrays;

public class ComplexJsonParsing {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(PayLoad.CoursePrice());

        int coursesCount = js.getInt("courses.size()");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");

        String firstCourseTitle = js.getString("courses[0].title");

        System.out.println(coursesCount);
        System.out.println(purchaseAmount);
        System.out.println(firstCourseTitle);

        // Print the title and price of all the courses
        for(int i=0; i < coursesCount; i++) {
            String title = js.getString(String.format("courses[%d].title", i));
            int price = js.getInt(String.format("courses[%d].price", i));
            System.out.println(title);
            System.out.println(price);
        }
    }
}
