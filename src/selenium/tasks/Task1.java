package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement numberField = driver.findElement(By.id("numb"));
        numberField.clear();
        numberField.sendKeys("testtext");
        driver.findElement(By.className("w3-btn")).click();
        String expectedR = "Please enter a number";
        String actualR = driver.findElement(By.xpath("//*[@id='ch1_error']")).getText();
        assertEquals(expectedR, actualR);
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement numberField = driver.findElement(By.id("numb"));
        numberField.clear();
        numberField.sendKeys("5");
        driver.findElement(By.className("w3-btn")).click();
        String expectedS = "Number is too small";
        String actualS = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expectedS, actualS);
    }

    @Test
    public void errorOnNumberTooBig() {
//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement numberField = driver.findElement(By.id("numb"));
        numberField.clear();
        numberField.sendKeys("222");
        driver.findElement(By.className("w3-btn")).click();
        String expectedS = "Number is too big";
        String actualS = driver.findElement(By.id("ch1_error")).getText();
        assertEquals(expectedS, actualS);
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement numberField = driver.findElement(By.id("numb"));
        numberField.clear();
        numberField.sendKeys("81");
        driver.findElement(By.className("w3-btn")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 81 is 9.00", alert.getText());
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement numberField = driver.findElement(By.id("numb"));
        numberField.clear();
        numberField.sendKeys("77");
        driver.findElement(By.className("w3-btn")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 77 is 8.77", alert.getText());
    }
}
