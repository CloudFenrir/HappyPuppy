package Utils;

import Pages.HomePage;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverSetup {

    private WebDriver driver;

    public DriverSetup() {

    }

    public DriverSetup(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setupDriver() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void quitDriver() {
        driver.quit();
    }

    public HomePage navigateHomePage() {
        driver.navigate().to("http://puppies.herokuapp.com/");
        return new HomePage(driver);
    }

}
