package uitesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverDemo_Selenium_3 {
    public static void main(String[] args) {
        // System.setProperty("webdriver.chrome.driver", "/Users/santosh/SAN/Software/chromedriver.83");
        // no need of manual install
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.google.com";
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println(title);
        String command = "DOM.getDocument";
        driver.close();
    }
}
