import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Traducao {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void traduzGato() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("palavra")).clear();
        driver.findElement(By.name("palavra")).sendKeys("gato");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("A tradução é: cat", driver.findElement(By.cssSelector("h1")).getText());
    }

    @Test
    public void traduzLuz() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("palavra")).clear();
        driver.findElement(By.name("palavra")).sendKeys("luz");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("A tradução é: light", driver.findElement(By.cssSelector("h1")).getText());
    }

    @Test
    public void retornaPalavraOriginalQuandoNaoAchaTraducao() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("palavra")).clear();
        driver.findElement(By.name("palavra")).sendKeys("ornitorrinco");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("A tradução é: ornitorrinco", driver.findElement(By.cssSelector("h1")).getText());
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