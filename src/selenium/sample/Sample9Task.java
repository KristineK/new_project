package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Sample9Task {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         * 1) click on start loading green button
        WebElement loadGreenButton = driver.findElement(By.cssSelector("#start_green"));
        assertTrue(loadGreenButton.isDisplayed());
        loadGreenButton.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        Thread.sleep(3000);
        assertFalse(loadGreenButton.isDisplayed());
        WebElement loadingGreenText = driver.findElement(By.cssSelector("#loading_green"));
//        WebElement loadingGreenText = driver.findElement(By.xpath("//*[contains(text(), 'Loading green...')]"));
        System.out.println(loadingGreenText.getText());
        assertEquals("Loading green...", loadingGreenText.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        Thread.sleep(3000);
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
//        WebElement greenFinished = driver.findElement(By.xpath("//*[contains(text(), ' Loaded')]"));
        WebElement greenFinished = driver.findElement(By.cssSelector("#finish_green"));
        System.out.println(greenFinished.getText());
        assertEquals("Green Loaded", greenFinished.getText());
    }

    @Test
    public void loadGreenmplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         * 1) click on start loading green button
        WebElement loadGreenButton = driver.findElement(By.cssSelector("#start_green"));
        assertTrue(loadGreenButton.isDisplayed());
        loadGreenButton.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreenText = driver.findElement(By.cssSelector("#loading_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement greenFinished = driver.findElement(By.cssSelector("#finish_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertEquals("Green Loaded", greenFinished.getText());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait;
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

//         * 1) click on start loading green button
        WebElement loadGreenButton = driver.findElement(By.cssSelector("#start_green"));
        assertTrue(loadGreenButton.isDisplayed());
        loadGreenButton.click();

//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loading_green")));
        WebElement loadingGreenText = driver.findElement(By.cssSelector("#loading_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish_green")));
        WebElement greenFinished = driver.findElement(By.cssSelector("#finish_green"));
        assertFalse(loadGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertEquals("Green Loaded", greenFinished.getText());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        WebDriverWait wait;
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

//         * 0) wait until button to load green and blue appears
        WebElement greenAndBlueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#start_green_and_blue")));

//         * 1) click on start loading green and blue button
        greenAndBlueButton.click();

//         * 2) check that button does not appear, but loading text is seen instead for green
        WebElement greenNoBlue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#loading_green_without_blue")));
        assertFalse(greenAndBlueButton.isDisplayed());
        assertEquals("Loading green...", greenNoBlue.getText());

//         * 3) check that button does not appear, but loading text is seen instead for green and blue
        WebElement greenWithBlue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#loading_green_with_blue")));
        assertFalse(greenAndBlueButton.isDisplayed());
        assertEquals("Loading green...", greenNoBlue.getText());
        assertEquals("Loading blue...", greenWithBlue.getText());

//         * 4) check that button and loading green does not appear,
//         * 		but loading text is seen instead for blue and success for green is seen
        WebElement greenFinished = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#loading_blue_without_green")));
        assertFalse(greenAndBlueButton.isDisplayed());
        assertFalse(greenNoBlue.isDisplayed());
        assertEquals("Loading blue...", greenWithBlue.getText());
        assertEquals("Green finished waiting for blue", greenFinished.getText());

//         * 5) check that both button and loading text is not seen, success is seen instead
        WebElement greenAndBlueFinished = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish_green_and_blue")));
        assertFalse(greenAndBlueButton.isDisplayed());
        assertFalse(greenNoBlue.isDisplayed());
        assertFalse(greenWithBlue.isDisplayed());
        assertFalse(greenFinished.isDisplayed());
        assertEquals("Green and Blue Loaded", greenAndBlueFinished.getText());
    }

}