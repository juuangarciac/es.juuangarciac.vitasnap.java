package es.juuangarciac.vitasnap.java.user.e2e;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import es.juuangarciac.vitasnap.java.user.ObjectMother;
import es.juuangarciac.vitasnap.user.domain.User;
import es.juuangarciac.vitasnap.user.services.UserManagementService;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @author juuangarciac
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserActivationE2E {
    private final String uribase = "http://127.0.0.1:";
    @LocalServerPort
    private int port;
    private WebDriver driver;
    private User testUser;

    @Autowired
    private UserManagementService userManagementService;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    // Configura el WebDriver para Chrome utilizando WebDriverManager.
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1200");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    // Este metodo se ejecuta despues de cada prueba. Se cierra el navegador web
    // (si está abierto), y se elimina al usuario de prueba del sistema.
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }

        if (testUser != null && testUser.getId() != null) {
            userManagementService.delete(testUser);
        }
    }

    @Test
    public void shouldNotActivateANoExistingUser() {

        // Given
        // a certain user
        testUser = ObjectMother.createTestUser();

        // point the browser to the activation page
        driver.get(uribase + port + "/useractivation");

        // and introduce form data
        driver.findElement(By.id("email")).sendKeys(testUser.getEmail());
        driver.findElement(By.id("secretCode")).sendKeys("key");

        // and press the activate button
        driver.findElement(By.id("activate")).click();

        // and
        WebElement element = driver.findElement(By.id("status"));

        assertThat(element.getText().equals("Ups. The user could not be activated")).isTrue();

    }


    @Test
    public void shouldActivateAnExistingUser() {

        // Given
        // a certain user
        testUser = ObjectMother.createTestUser();

        //who is registered
        userManagementService.registerUser(testUser);

        // When

        // point the browser to the activation page
        driver.get(uribase + port + "/useractivation");

        // and introduce form data
        driver.findElement(By.id("email")).sendKeys(testUser.getEmail());
        driver.findElement(By.id("secretCode")).sendKeys(testUser.getRegisterCode());

        // and press the activate button
        driver.findElement(By.id("activate")).click();

        // Then
        WebElement element = driver.findElement(By.id("status"));

        assertThat(element.getText().equals("Congrats. The user has been activated")).isTrue();

    }

}
