package files;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

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

        // Print the number of copies sold for a specific course
        String courserTitle = "Cypress";
        int copiesSold = 0;
        for(int i=0; i< coursesCount; i++) {
            if(js.getString(String.format("courses[%d].title", i)).equals(courserTitle)) {
                copiesSold = js.get(String.format("courses[%d].copies", i));
                break;
            }
        }
        System.out.println(String.format("Copies sold = %d", copiesSold));

        // Verify if purchase amount matches the sum of course prices
        int totalPriceOfCourses = 0;
        for(int i=0; i< coursesCount; i++) {
            int coursePrice = js.getInt(String.format("courses[%d].price", i));
            int numCopies = js.getInt(String.format("courses[%d].copies", i));
            totalPriceOfCourses = totalPriceOfCourses + ( coursePrice * numCopies );
        }
        System.out.println(String.format("sum of prices of all courses = %d", totalPriceOfCourses));
        Assert.assertEquals(totalPriceOfCourses, purchaseAmount);
    }
}
