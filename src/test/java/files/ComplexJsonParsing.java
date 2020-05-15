package files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(PayLoad.CoursePrice());
        // Print the number of courses returned.
        int coursesCount = js.getInt("courses.size()");
        System.out.println(coursesCount);
    }
}
