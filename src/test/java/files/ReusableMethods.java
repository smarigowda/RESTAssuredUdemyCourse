package files;

import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class ReusableMethods {
    public static JsonPath rawToJson(String response) {
        return new JsonPath(response);
    }
    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(readAllBytes(Paths.get(path)));
    }
}
