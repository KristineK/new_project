package selenium.sample.extra;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.junit.Assert.assertEquals;


public class extra2Task {
    WebDriver driver;
    String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
    String po_url = "https://kristinek.github.io/site/examples/po";

    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void runningOnFirefox() throws Exception {
        System.setProperty("webdriver.gecko.driver", libWithDriversLocation + "geckodriver.exe");
        driver = new FirefoxDriver();
//        TODO
//        go to page https://kristinek.github.io/site/examples/po
        driver.get(po_url);
//        check the background color of h1 element
        WebElement h1 = driver.findElement(By.xpath("//h1"));
        System.out.println(h1.getCssValue("background-color"));
        assertEquals("rgba(0, 0, 0, 0)", h1.getCssValue("background-color"));
        Thread.sleep(5000);


    }

    @Test
    public void runningOnChrome() throws Exception {
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
//        TODO
//        go to page https://kristinek.github.io/site/examples/po
        driver.get(po_url);
//        check the background color of h1 element
        WebElement h1 = driver.findElement(By.xpath("//h1"));
        System.out.println(h1.getCssValue("background-color"));
        assertEquals("rgba(0, 0, 0, 0)", h1.getCssValue("background-color"));
        Thread.sleep(5000);
    }

    @Test
    public void runningOnIE() throws Exception {
        System.setProperty("webdriver.ie.driver", libWithDriversLocation + "IEDriverServer.exe");
        driver = new InternetExplorerDriver();
//        TODO
//        go to page https://kristinek.github.io/site/examples/po
        driver.get(po_url);
//        check the background color of h1 element
        WebElement h1 = driver.findElement(By.xpath("//h1"));
        System.out.println(h1.getCssValue("background-color"));
        assertEquals("rgba(0, 0, 0, 0)", h1.getCssValue("background-color"));
        Thread.sleep(5000);
    }
}
