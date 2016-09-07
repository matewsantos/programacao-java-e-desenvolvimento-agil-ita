import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PaginaConversaoTemperatura {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private final StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void fahrenheitParaCelsius() throws Exception {
    driver.get(baseUrl + "/conversor/");
    new Select(driver.findElement(By.name("conversor"))).selectByVisibleText("Fahrenheit para Celsius");
    driver.findElement(By.name("temperatura")).clear();
    driver.findElement(By.name("temperatura")).sendKeys("212");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("100.0", driver.findElement(By.cssSelector("h1")).getText());
  }
  
  @Test
  public void celsiusParaFahrenheit() throws Exception {
    driver.get(baseUrl + "/conversor/");
    new Select(driver.findElement(By.name("conversor"))).selectByVisibleText("Celsius para Fahrenheit");
    driver.findElement(By.name("temperatura")).clear();
    driver.findElement(By.name("temperatura")).sendKeys("100");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals("212.0", driver.findElement(By.cssSelector("h1")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
