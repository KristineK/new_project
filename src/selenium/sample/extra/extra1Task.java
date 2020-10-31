package selenium.sample.extra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/act";
    String new_url = "https://kristinek.github.io/site/examples/link1";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void navigateBack() throws Exception {
//        open page with url "https://kristinek.github.io/site/examples/po"
        driver.get("https://kristinek.github.io/site/examples/po");
//        click "More > " for the top left element
        WebElement moreButton = driver.findElement(By.xpath("//body/div[3]/div[2]/div[1]/div[1]/p[1]/a[1]"));
        moreButton.click();
        Thread.sleep(1000);
//        check that the url now "https://kristinek.github.io/site/examples/po1"
        assertEquals("https://kristinek.github.io/site/examples/po1", driver.getCurrentUrl());

//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
        driver.navigate().back();
        Thread.sleep(1000);

//        check that the page now is "https://kristinek.github.io/site/examples/po"
        assertEquals("https://kristinek.github.io/site/examples/po", driver.getCurrentUrl());

    }

    @Test
    public void navigateForward() throws Exception {
//        open page with url "https://kristinek.github.io/site/examples/po"

        driver.get("https://kristinek.github.io/site/examples/po");
//        click "More > " for the top left element
        WebElement moreButton = driver.findElement(By.xpath("//body/div[3]/div[2]/div[1]/div[1]/p[1]/a[1]"));
        moreButton.click();
        Thread.sleep(1000);

//        using driver navigation go back to "https://kristinek.github.io/site/examples/po"
        driver.navigate().back();
        Thread.sleep(1000);

//        using driver navigation go forward to "https://kristinek.github.io/site/examples/po1"
        driver.navigate().forward();
        Thread.sleep(1000);

//        check that the page now is "https://kristinek.github.io/site/examples/po1"
        assertEquals("https://kristinek.github.io/site/examples/po1", driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {
//        open page "https://kristinek.github.io/site/examples/act"
        driver.get("https://kristinek.github.io/site/examples/actions");

//        click on "Show" button in 'Button' section
        WebElement showButton = driver.findElement(By.xpath("//button[@id='show_text']"));
        showButton.click();

//        check that text "I am here!" is seen
        assertTrue(driver.findElement(By.cssSelector("#show_me")).isDisplayed());

//        refresh page
        driver.navigate().refresh();

//        check that text "I am here!" is not seen
        assertFalse(driver.findElement(By.cssSelector("#show_me")).isDisplayed());
    }
}
