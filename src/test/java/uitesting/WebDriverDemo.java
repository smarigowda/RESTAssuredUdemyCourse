package uitesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverDemo {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/santosh/SAN/Software/chromedriver");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "http://demo.guru99.com/test/newtours/";
        driver.get(baseUrl);
        driver.close();
    }
}
