package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static WebDriver createDriver() {

        //Setting Chrome Driver path
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver-linux64/chromedriver");

        //Setting Chrome Profile
        ChromeOptions options = new ChromeOptions();

        options.setBinary("/usr/bin/google-chrome");
        options.addArguments("--disable-extensions"); // disabling extensions
        //options.addArguments("--disable-gpu"); // applicable to windows os only
//        options.addArguments("--headless=new");
        options.addArguments("start-maximized", "--disable-infobars", "--remote-allow-origins=*", "--disable-dev-shm-usage", "--no-sandbox");
        options.addArguments("user-data-dir=/home/yash/.config/google-chrome/AutomationProfile0");
        System.out.println("here1");
        WebDriver driver = new ChromeDriver(options);
        System.out.println("here2");
        //Adding Implicit wait that applies to all elements to avoid ElementNotFoundException
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }
}
