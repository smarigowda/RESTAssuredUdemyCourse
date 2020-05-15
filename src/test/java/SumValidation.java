import files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCoursePrices() {
        JsonPath js = new JsonPath(PayLoad.CoursePrice());
        int coursesCount = js.getInt("courses.size()");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");

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
