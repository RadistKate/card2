import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");

    }


    @AfterEach
    void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestSuccessOrderIfCorrectFilling() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Андрей Петров-Новиков");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("+78208208208");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] ")).click();
        driver.findElement(By.cssSelector("button.button ")).click();
        var actualTest = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualTest);
    }

}







