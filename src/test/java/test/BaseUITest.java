package test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseUITest {
    WebDriver driver;
    public final String url = "https://qa-scooter.praktikum-services.ru/";
    @Before
    public void createDriver() {
        initFireFox();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {
        driver.close();
    }

    public void initChrome() {
        driver = new ChromeDriver();
    }

    public void initFireFox() {
        driver = new FirefoxDriver();
    }
}
