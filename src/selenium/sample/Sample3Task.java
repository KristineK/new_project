package selenium.sample;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class Sample3Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
//         check how many element with class "test" there are on page (5)
        System.out.println(driver.findElements(By.className("test")).size());

//         check that value of second button is "This is also a button"
        String expected1 = "This is also a button";
        String actual1 = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertEquals(expected1, actual1);
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
        String elementTextOnPage = driver.findElement(By.id("buttonId")).getAttribute("value");
        assertTrue(elementTextOnPage.equalsIgnoreCase("this is Also a Button"));
//         fail with custom error message:
        System.err.println("We failed ");
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        String elementTextOnPage = driver.findElement(By.xpath("/html/body/input[1]")).getText();
        assertFalse(elementTextOnPage.equals("This is a button"));
        assertFalse(false);

    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));
        for (WebElement elementWithClass : allElementsWithClass ) {
            assertFalse(elementWithClass.getText() .contains("190"));
        }
    }
}
