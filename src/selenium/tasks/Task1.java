package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.AgeSamplePage;
import selenium.pages.AgeSubmittedSamplePage;

import static java.lang.Math.sqrt;
import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class Task1 {

    static WebDriver driver;
    static AgeSamplePage agePage;
    static AgeSubmittedSamplePage ageSubmittedPage;


    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
        agePage = PageFactory.initElements(driver, AgeSamplePage.class);
        ageSubmittedPage = PageFactory.initElements(driver, AgeSubmittedSamplePage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() throws Exception {
//        TODO
//        enter a text instead of a number, check that correct error is seen

        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys("qwe");
        WebElement submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submit.click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
        //driver.findElement(By.xpath("//*[contains(text(), 'Please enter a number')]")).getText();

    }

    @Test
    public void errorOnNumberTooSmall() throws Exception {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen

        // Bug with num 49!

        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys("49");
        WebElement submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submit.click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
        //driver.findElement(By.xpath("//*[contains(text(), 'Number is too small')]")).getText();

    }

    @Test
    public void errorOnNumberTooBig() throws Exception {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        inputField.sendKeys("101");
        WebElement submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submit.click();
        assertTrue(driver.findElement(By.id("ch1_error")).isDisplayed());
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
        //driver.findElement(By.xpath("//*[contains(text(), 'Number is too big')]")).getText();

    }



    @Test
    public void correctSquareRootWithoutRemainder() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly


        // Perhaps it would be better to do the input in a way that at the
        // beginning boundary values are located from input field, if they are given, for example
        // driver.findElement(By.xpath("//b[contains(text(),'Please enter a number from 50 to 100')]"));
        // (so in this case 50 and 100) then values used to make boundary values for input automatically,
        // like 50=x and 100=y, then use the values x-1, x, y, y+1. I will try to do it, but if you see
        // this text it means I didn't have enough time. Thank you for your attention :)

        // P.S. And I might be wrong, because we create the tests for each specific
        // case and define the values accordingly, so perhaps it's better to just stick
        // with some predefined values. I will be happy to get the correct answer/explanation!

        // Also not sure what would be the correct way for expected outcome, but I assume that we need to
        // just enter our defined values. Anyway I tried also to calculate them to see the outcome (below)


            WebElement inputField = driver.findElement(By.id("numb"));
            inputField.clear();

            String value = "100";
            inputField.sendKeys(value);


            WebElement submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
            submit.click();

        try {
            WebElement error = driver.findElement(By.id("ch1_error"));
            assertFalse(error.isDisplayed());
        }
            catch (Exception e) {
            }

            finally{
                Alert alert = driver.switchTo().alert();


                assertEquals("Square root of " + value + " is 10.00", alert.getText());


            }

        }

    @Test
    public void correctSquareRootWithoutRemainder2() throws Exception {

        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        int num = 100;
        double sqNum = sqrt(num);
        String value = String.valueOf(num);
        inputField.sendKeys(value);

        WebElement submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submit.click();
        //Thread.sleep(5000);

        try {
            WebElement error = driver.findElement(By.id("ch1_error"));
            assertFalse(error.isDisplayed());
        }
        catch (Exception e) {
        }

        finally{
            Alert alert = driver.switchTo().alert();

            //String sqv = String.valueOf(sqNum);
            //System.out.println(sqv);

            DecimalFormat df = new DecimalFormat("####.00");
            //System.out.println(df.format(sqNum));

            assertEquals("Square root of " + num + " is " + df.format(sqNum), alert.getText());


            //assertTrue(driver.findElement(By.id("textForAlerts")).getText().contains(sqv));

        }

    }





    @Test
    public void correctSquareRootWithRemainder() throws Exception {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly

        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();

        String value = "50";
        inputField.sendKeys(value);


        WebElement submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submit.click();

        try {
            WebElement error = driver.findElement(By.id("ch1_error"));
            assertFalse(error.isDisplayed());
        } catch (Exception e) {
        } finally {
            Alert alert = driver.switchTo().alert();


            assertEquals("Square root of " + value + " is 7.07", alert.getText());


        }
    }

    @Test
    public void correctSquareRootWithRemainder2() throws Exception {

        WebElement inputField = driver.findElement(By.id("numb"));
        inputField.clear();
        int num = 50;
        double sqNum = sqrt(num);
        String value = String.valueOf(num);
        inputField.sendKeys(value);

        WebElement submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submit.click();
        //Thread.sleep(5000);

        try {
            WebElement error = driver.findElement(By.id("ch1_error"));
            assertFalse(error.isDisplayed());
        }
        catch (Exception e) {
        }

        finally{
            Alert alert = driver.switchTo().alert();

            //String sqv = String.valueOf(sqNum);
            //System.out.println(sqv);

            DecimalFormat df = new DecimalFormat("####.00");
            //System.out.println(df.format(sqNum));

            assertEquals("Square root of " + num + " is " + df.format(sqNum), alert.getText());


            //assertTrue(driver.findElement(By.id("textForAlerts")).getText().contains(sqv));

        }
}
}
