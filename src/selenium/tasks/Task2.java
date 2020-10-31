package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        WebElement nameField = driver.findElement(By.id("fb_name")); //name
        nameField.clear();
        WebElement ageField = driver.findElement(By.id("fb_age"));  //age
        ageField.clear();

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']")); //language
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        WebElement commentField = driver.findElement(By.xpath("//*[@id='fb_form']/form/div[6]/textarea")); //comment
        commentField.clear();

//         "Don't know" is selected in "Genre"
        WebElement radioButton3 = driver.findElement(By.xpath("//*[@id='fb_form']/form/div[4]/input[3]"));
        assertTrue(radioButton3.isSelected());

//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Why me?");
        assertEquals("Why me?", dropdown.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.xpath("//*[@id='fb_form']/form/button"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", sendButton.getCssValue("text-decoration-color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         click "Send" without entering any data
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();

//        check fields are empty or null
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-btn.w3-green.w3-xlarge"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));

        WebElement noButton = driver.findElement(By.cssSelector("button.w3-btn.w3-red.w3-xlarge"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", noButton.getCssValue("text-decoration-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form,
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Irina");
        WebElement age = driver.findElement(By.id("fb_age"));
        Thread.sleep(1000);
        age.sendKeys("36");
        Thread.sleep(1000);
        WebElement engLanguage = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='English']"));
        engLanguage.click();
        Thread.sleep(1000);
        WebElement female = driver.findElement(By.cssSelector(".w3-radio[type='radio'][value=female]"));
        female.click();
        Thread.sleep(1000);
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        Thread.sleep(1000);
        WebElement comment = driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea"));
        comment.sendKeys("this is a test");
        Thread.sleep(1000);

//          click "Send"
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();

//         check fields are filled correctly
        assertEquals("Irina", driver.findElement(By.id("name")).getText());
        assertEquals("36", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Good", driver.findElement(By.id("option")).getText());
        assertEquals("this is a test", driver.findElement(By.id("comment")).getText());
        Thread.sleep(3000);

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-btn.w3-green.w3-xlarge"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));

        WebElement noButton = driver.findElement(By.cssSelector("button.w3-btn.w3-red.w3-xlarge"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", noButton.getCssValue("text-decoration-color"));

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Name");
        Thread.sleep(2000);

//         click "Send"
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();
        Thread.sleep(2000);

//         click "Yes"
        driver.findElement(By.cssSelector("button.w3-btn.w3-green.w3-xlarge")).click();

//         check message text: "Thank you, NAME, for your feedback!"
        String yesMessage = ("Thank you, Name, for your feedback!");
        assertEquals(yesMessage, driver.findElement(By.id("message")).getText());
        Thread.sleep(1000);

//         color of text is white with green on the background
        WebElement messageField = driver.findElement(By.cssSelector("div.w3-panel.w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", messageField.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", messageField.getCssValue("color"));

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything)
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();

//         click "Yes"
        driver.findElement(By.cssSelector("button.w3-btn.w3-green.w3-xlarge")).click();

//         check message text: "Thank you for your feedback!"
        String feedbackMessage = ("Thank you for your feedback!");
        assertEquals(feedbackMessage, driver.findElement(By.id("message")).getText());
        Thread.sleep(1000);

//         color of text is white with green on the background
        WebElement messageField = driver.findElement(By.cssSelector("div.w3-panel.w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", messageField.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", messageField.getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Irina");
        WebElement age = driver.findElement(By.id("fb_age"));
        Thread.sleep(500);
        age.sendKeys("36");
        Thread.sleep(500);
        WebElement engLanguage = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='English']"));
        engLanguage.click();
        Thread.sleep(500);
        WebElement female = driver.findElement(By.cssSelector(".w3-radio[type='radio'][value=female]"));
        female.click();
        Thread.sleep(500);
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        Thread.sleep(500);
        WebElement comment = driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea"));
        comment.sendKeys("this is a test");
        Thread.sleep(500);

//         click "Send"
        driver.findElement(By.xpath("//*[@id='fb_form']/form/button")).click();
        Thread.sleep(1000);

//         click "No"
        driver.findElement(By.cssSelector("button.w3-btn.w3-red.w3-xlarge")).click();
        Thread.sleep(1000);

//         check fields are filled correctly
        WebElement nameField = driver.findElement(By.id("fb_name"));
        String userName = "Irina";
        assertEquals(nameField.getText(), ""); // checking that getText is empty
        assertEquals(nameField.getAttribute("value"), userName);

        WebElement ageField = driver.findElement(By.id("fb_age"));
        String userAge = "36";
        assertEquals(ageField.getText(), ""); // checking that getText is empty
        assertEquals(ageField.getAttribute("value"), userAge);
        Thread.sleep(1000);

        WebElement engOption = driver.findElement(By.cssSelector(".w3-check[type='checkbox'][value='English']"));
        assertTrue(engOption.isSelected());

        WebElement femOption = driver.findElement(By.cssSelector(".w3-radio[type='radio'][value=female]"));
        assertTrue(femOption.isSelected());

        Select newDropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Good", newDropdown.getFirstSelectedOption().getText());

        WebElement commentField = driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea"));
        assertEquals(commentField.getText(), ""); // checking that getText is empty
        assertEquals(commentField.getAttribute("value"), "this is a test");
    }
}
