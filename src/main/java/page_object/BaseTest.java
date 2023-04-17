package page_object;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static page_object.MainPage.PAGE_URL;

public class BaseTest {
    public WebDriver driver;

    @Before
    public void setUp() {
        // драйвер для браузера Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(PAGE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
