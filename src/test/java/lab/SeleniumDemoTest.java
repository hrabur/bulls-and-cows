package lab;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumDemoTest {
  WebDriver driver;

  @BeforeAll
  static void setupAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void setup() {
    driver = new ChromeDriver();
  }

  @AfterEach
  void teardown() {
    driver.quit();
  }

  @Test
  void searchInGoogleWithExplicitWait() {
    driver.get("https://google.com");
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Приемане на всички']")))
        .click();
    var searchInput = driver.findElement(By.name("q"));
    new Actions(driver)
        .moveToElement(searchInput)
        .click()
        .sendKeys("selenium")
        .sendKeys(Keys.RETURN)
        .perform();
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a/h3[text()='Selenium']")));
  }

  @Test
  void searchInGoogleWithImplicitWait() {
    driver.get("https://google.com");
    driver.findElement(By.xpath("//div[text()='Приемане на всички']")).click();
    var searchInput = driver.findElement(By.name("q"));
    searchInput.click();
    searchInput.sendKeys("Selenium");
    searchInput.sendKeys(Keys.ENTER);
    driver.findElement(By.xpath("//a/h3[text()='Selenium']")).click();
  }
}
