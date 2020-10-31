package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample6Task {
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
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        driver.findElement(By.id("heading_2")).getText();
        driver.findElement(By.xpath("//h2[@id='heading_2']")).getText();
        driver.findElement(By.xpath("/html[1]/body[1]/h2[2]")).getText();
        //System.out.println(driver.findElement(By.id("heading_2")).getText());
        //System.out.println(driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
        //System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/h2[2]")).getText());

//        1-2 ways to find text: "Test Text 1"
        driver.findElement(By.xpath("//p[contains(text(),'Test Text 1')]")).getText();
        driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(6) > p.test")).getText();
        driver.findElement(By.xpath("/html[1]/body[1]/div[4]/p[1]")).getText();
        //System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Test Text 1')]")).getText());
        //System.out.println(driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(6) > p.test")).getText());
        //System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[4]/p[1]")).getText());

//        1-2 ways to find text: "Test Text 2"
        driver.findElement(By.xpath("//p[contains(text(),'Test Text 2')]")).getText();
        driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(6) > p.twoTest")).getText();
        driver.findElement(By.xpath("/html[1]/body[1]/div[4]/p[2]")).getText();
        //System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Test Text 2')]")).getText());
        //System.out.println(driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(6) > p.twoTest")).getText());
        //System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[4]/p[2]")).getText());

//        1-2 ways to find text: "Test Text 3"
        driver.findElement(By.xpath("//p[contains(text(),'Test Text 3')]")).getText();
        driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(7) > p.test:nth-child(1)")).getText();
        driver.findElement(By.xpath("/html[1]/body[1]/div[5]/p[1]")).getText();
        //System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Test Text 3')]")).getText());
        //System.out.println(driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(7) > p.test:nth-child(1)")).getText());
        //System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[5]/p[1]")).getText());

//        1-2 ways to find text: "Test Text 4"
        driver.findElement(By.xpath("//p[contains(text(),'Test Text 4')]")).getText();
        driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(7) > p.test:nth-child(2)")).getText();
        driver.findElement(By.xpath("/html[1]/body[1]/div[5]/p[2]")).getText();
        //System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Test Text 4')]")).getText());
        //System.out.println(driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(7) > p.test:nth-child(2)")).getText());
        //System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[5]/p[2]")).getText());

//        1-2 ways to find text: "Test Text 5"
        driver.findElement(By.xpath("//p[contains(text(),'Test Text 5')]")).getText();
        driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(8) > p.Test")).getText();
        driver.findElement(By.xpath("/html[1]/body[1]/div[6]/p[1]")).getText();
        //System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Test Text 5')]")).getText());
        //System.out.println(driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(8) > p.Test")).getText());
        //System.out.println(driver.findElement(By.xpath("/html[1]/body[1]/div[6]/p[1]")).getText());

//        1-2 ways to find text: "This is also a button"
        driver.findElement(By.id("buttonId")).getAttribute("value");
        driver.findElement(By.name("randomButton2")).getAttribute("value");
        driver.findElement(By.xpath("//input[@id='buttonId']")).getAttribute("value");
        //System.out.println(driver.findElement(By.id("buttonId")).getAttribute("value"));
        //System.out.println(driver.findElement(By.name("randomButton2")).getAttribute("value"));
        //System.out.println(driver.findElement(By.xpath("//input[@id='buttonId']")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println("\t The text of element which contains text 'Heading 2 text' is '" +
                driver.findElement(By.xpath("//*[contains(text(), 'Heading 2 text')]")).getText() + "'");

//        1-2 ways to find text: "Test Text 1"
        System.out.println("\t The text of element which contains text 'Test Text 1' is '" +
                driver.findElement(By.xpath("//*[contains(text(), 'Test Text 1')]")).getText() + "'");

//        1-2 ways to find text: "Test Text 2"
        System.out.println("\t The text of element which contains text 'Test Text 2' is '" +
                driver.findElement(By.xpath("//*[contains(text(), 'Test Text 2')]")).getText() + "'");

//        1-2 ways to find text: "Test Text 3"
        System.out.println("\t The text of element which contains text 'Test Text 3' is '" +
                driver.findElement(By.xpath("//*[contains(text(), 'Test Text 3')]")).getText() + "'");

//        1-2 ways to find text: "This is also a button"
        System.out.println("\t The name of element with value 'This is also a button' is '" +
                driver.findElement(By.xpath("//*[@value='This is also a button']")).getAttribute("name") + "'");
        System.out.println("\t The name of element with value 'This is also a button' is '" +
                driver.findElement(By.cssSelector("[value='This is also a button']")).getAttribute("name") + "'");
    }


}
