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
import static org.junit.Assert.assertFalse;

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
        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.tagName("button"));

        inputField.sendKeys("text");
        button.click();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
       assertEquals("Please enter a number", errorMessage.getText());

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.tagName("button"));

        inputField.sendKeys("4");
        button.click();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too small", errorMessage.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.tagName("button"));

        inputField.sendKeys("121");
        button.click();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too big", errorMessage.getText());
    }
    @Test
    public void errorOnNumberTooBig666() {

//        BUG: if I enter number 666 no errors where seen
//        TEST FAILS

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.tagName("button"));

        inputField.sendKeys("666");
        button.click();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertEquals("Number is too big", errorMessage.getText());
    }
    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.tagName("button"));

        inputField.sendKeys("64");
        button.click();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        assertEquals("Square root of 64 is 8.00",alert.getText());
        alert.accept();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertFalse(errorMessage.isDisplayed());


    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        WebElement inputField = driver.findElement(By.id("numb"));
        WebElement button = driver.findElement(By.tagName("button"));

        inputField.sendKeys("60");
        button.click();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        assertEquals("Square root of 60 is 7.75",alert.getText());
        alert.accept();

        WebElement errorMessage = driver.findElement(By.id("ch1_error"));
        assertFalse(errorMessage.isDisplayed());


    }
}
