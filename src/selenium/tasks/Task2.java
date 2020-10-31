package selenium.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.AgeSamplePage;
import selenium.pages.AgeSubmittedSamplePage;
import java.awt.Color;
import javax.swing.text.AttributeSet;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import sun.awt.resources.awt;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.Color.*;

public class Task2 {
    static WebDriver driver;
    static AgeSamplePage agePage;
    static AgeSubmittedSamplePage ageSubmittedPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        agePage = PageFactory.initElements(driver, AgeSamplePage.class);
        ageSubmittedPage = PageFactory.initElements(driver, AgeSubmittedSamplePage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked


        List<WebElement> inputFields = driver.findElements(By.cssSelector(".w3-input w3-border[type='text']"));
        for (WebElement inputField : inputFields) {
            assertFalse(inputField.isSelected());
        }
        boolean condition = false;

        for (WebElement inputField : inputFields) {
            if (inputField.getAttribute("value").isEmpty()) {
                condition = true;
            }
            assertTrue(condition);
        }

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }


        List<WebElement> radios = driver.findElements(By.cssSelector(".w3-radio[type='radio']"));
        for (WebElement radio : radios) {
            assertFalse(radio.isSelected()); // "Don't know" is selected in "Genre"
        }

        WebElement select = driver.findElement(By.id("like_us"));
        assertFalse(select.isSelected());


        WebElement textArea = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[6]/textarea[1]"));
        assertFalse(textArea.isSelected());


        if (textArea.getAttribute("value").isEmpty()) {
            condition = true;
        }
        assertTrue(condition);


//         "Don't know" is selected in "Genre"
        WebElement radioDisabled = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[4]/input[3]"));
        assertTrue(radioDisabled.isSelected());


//         "Choose your option" in "How do you like us?"
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");

//         check that the button send is blue with white letters
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));

        String whiteColor = "rgba(255, 255, 255, 1)";
        //String blueColor = "rgba(0, 0, 255, 1)";
        String expectedBlue = "rgba(33, 150, 243, 1)";

        //assertEquals(blueColor, button.getCssValue("background-color"));
        //System.out.println(button.getCssValue("background-color"));

        assertEquals(expectedBlue, button.getCssValue("background-color"));
        //System.out.println(button.getCssValue("background-color"));

        assertEquals(whiteColor, button.getCssValue("color"));
        //System.out.println(button.getCssValue("color"));

        // I tried to get color names out of RGB or hex values to compare also
        // that way (and also the other way round) but didn't succeed. However I assume
        // that the method with just checking RGB color might be correct, as we
        // create tests from specifications, and expected values should be given?

    }


        @Test
        public void emptyFeedbackPage() throws Exception {

//         TODO:
//         click "Send" without entering any data
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
        button.click();

//         check fields are empty or null

            /* Sorry, this is the incorrect initial way how I did that. The correct way is below.
            WebElement field1 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]"));
            WebElement field2 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/p[1]"));
            WebElement field3 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/p[1]"));
            WebElement field4 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[4]/p[1]"));
            WebElement field5 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[5]/p[1]"));
            WebElement field6 = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[6]/p[1]"));

            String empty = "";

            boolean condition = false;
            if(field1.getText().equals(("Your name:" + empty)) || field1.getText().equals(("Your name:" + " " + null))) {
                condition = true;
            }            assertTrue(condition);

            if(field2.getText().equals(("Your age:" + empty)) || field2.getText().equals(("Your age:" + " " + null))) {
                condition = true;
            }            assertTrue(condition);

            if(field3.getText().equals(("Your language:" + empty)) || field3.getText().equals(("Your language:" + " " + null))) {
                condition = true;
            }            assertTrue(condition);

            if(field4.getText().equals(("Your genre:" + empty)) || field4.getText().equals(("Your name:" + " " + null))) {
                condition = true;

            }        assertTrue(condition);

            if(field5.getText().equals(("Your option of us:" + empty)) || field5.getText().equals(("Your option of us:" + " " + null))) {
                condition = true;

            }        assertTrue(condition);

            if(field6.getText().equals(("Your comment:" + empty)) || field6.getText().equals(("Your comment:" + " " + null))) {
                condition = true;

            }        assertTrue(condition);
            */


            // This is he correct and easier way I discovered later on, as I discovered
            // span fields when checking values in filled form
            WebElement responseName = driver.findElement(By.id("name"));
            WebElement responseAge = driver.findElement(By.id("age"));
            WebElement responseLanguage = driver.findElement(By.id("language"));
            WebElement responseGender = driver.findElement(By.id("gender"));
            WebElement responseOption = driver.findElement(By.id("option"));
            WebElement responseComment = driver.findElement(By.id("comment"));

            ArrayList<WebElement> fields = new ArrayList<>();
            fields.add(responseName);
            fields.add(responseAge);
            fields.add(responseLanguage);
            fields.add(responseGender);
            fields.add(responseOption);
            fields.add(responseComment);

            boolean condition = false;

            for (WebElement field : fields) {
                if (field.getAttribute("value") == null) {
                    condition = true;
                }
                assertTrue(condition);
            }

//         check button colors
//         (green with white letter and red with white letters)
                WebElement button1 = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
                WebElement button2 = driver.findElement(By.xpath("//button[contains(text(),'No')]"));

                //String redColor = "rgba(255, 0, 0, 1)";
                //String greenColor = "rgba(0, 255, 0, 1)";
                String expectedRed = "rgba(244, 67, 54, 1)";
                String expectedGreen = "rgba(76, 175, 80, 1)";
                String whiteColor = "rgba(255, 255, 255, 1)";

                assertEquals(expectedGreen, button1.getCssValue("background-color"));
                assertEquals(whiteColor, button1.getCssValue("color"));

                assertEquals(expectedRed, button2.getCssValue("background-color"));
                assertEquals(whiteColor, button2.getCssValue("color"));
            }

        @Test
        public void notEmptyFeedbackPage() throws Exception {

//         TODO:
//         fill the whole form, click "Send"
            WebElement inputName = driver.findElement(By.xpath("//input[@id='fb_name']"));
            String name = "Name";
            inputName.sendKeys(name);
            WebElement inputAge = driver.findElement(By.xpath("//input[@id='fb_age']"));
            String age = "25";
            inputAge.sendKeys(age);

            WebElement checkBoxEnglish = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[3]/input[1]"));
            List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
            checkBoxEnglish.click();

            WebElement selectMale = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[4]/input[1]"));
            selectMale.click();
            Select dropdown = new Select(driver.findElement(By.id("like_us")));
            String dropDownSelect = "Ok, i guess";
            dropdown.selectByVisibleText(dropDownSelect);

            WebElement textArea = driver.findElement(By.tagName("textarea"));
            String testText = "This is a test text";
            textArea.sendKeys(testText);

            //Thread.sleep(3000);

            WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
            button.click();

            //Thread.sleep(3000);

//         check fields are filled correctly
            WebElement responseName = driver.findElement(By.id("name"));
            WebElement responseAge = driver.findElement(By.id("age"));
            WebElement responseLanguage = driver.findElement(By.id("language"));
            WebElement responseGender = driver.findElement(By.id("gender"));
            WebElement responseOption = driver.findElement(By.id("option"));
            WebElement responseComment = driver.findElement(By.id("comment"));

            assertEquals(name, responseName.getText());
            assertEquals(age, responseAge.getText());

            assertEquals("English", responseLanguage.getText());
            assertEquals("male", responseGender.getText());

            assertEquals(dropDownSelect, responseOption.getText());
            assertEquals(testText, responseComment.getText());


//         check button colors
//         (green with white letter and red with white letters)

            WebElement buttonYes = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
            WebElement buttonNo = driver.findElement(By.xpath("//button[contains(text(),'No')]"));

            //String redColor = "rgba(255, 0, 0, 1)";
            //String greenColor = "rgba(0, 255, 0, 1)";
            String expectedRed = "rgba(244, 67, 54, 1)";
            String expectedGreen = "rgba(76, 175, 80, 1)";
            String whiteColor = "rgba(255, 255, 255, 1)";

            assertEquals(expectedGreen, buttonYes.getCssValue("background-color"));
            assertEquals(whiteColor, buttonYes.getCssValue("color"));

            assertEquals(expectedRed, buttonNo.getCssValue("background-color"));
            assertEquals(whiteColor, buttonNo.getCssValue("color"));

        }

        @Test
        public void yesOnWithNameFeedbackPage() throws Exception {

//         TODO:
//         enter only name
            WebElement inputName = driver.findElement(By.xpath("//input[@id='fb_name']"));
            String name = "NAME";
            inputName.sendKeys(name);

//         click "Send"
            WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
            button.click();

//         click "Yes"
            WebElement buttonYes = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
            buttonYes.click();
            //Thread.sleep(3000);

//         check message text: "Thank you, NAME, for your feedback!"
            WebElement yesMessage = driver.findElement(By.id("message"));
            assertEquals("Thank you, " + name + ", for your feedback!", yesMessage.getText());

//         color of text is white with green on the background
            WebElement messageBlock = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]"));
            //String greenColor = "rgba(0, 255, 0, 1)";
            String expectedGreen = "rgba(76, 175, 80, 1)";
            String whiteColor = "rgba(255, 255, 255, 1)";
            assertEquals(expectedGreen, messageBlock.getCssValue("background-color"));
            assertEquals(whiteColor, messageBlock.getCssValue("color"));

        }

        @Test
        public void yesOnWithoutNameFeedbackPage() throws Exception {

//         TODO:
//         click "Send" (without entering anything
            WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
            button.click();

//         click "Yes"
            WebElement buttonYes = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
            buttonYes.click();

//         check message text: "Thank you for your feedback!"
            WebElement yesMessage = driver.findElement(By.id("message"));
            assertEquals("Thank you for your feedback!", yesMessage.getText());

//         color of text is white with green on the background
            WebElement messageBlock = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[1]"));
            //String greenColor = "rgba(0, 255, 0, 1)";
            String expectedGreen = "rgba(76, 175, 80, 1)";
            String whiteColor = "rgba(255, 255, 255, 1)";
            assertEquals(expectedGreen, messageBlock.getCssValue("background-color"));
            assertEquals(whiteColor, messageBlock.getCssValue("color"));

        }

        @Test
        public void noOnFeedbackPage() throws Exception {

//         TODO:
//         fill the whole form
            WebElement inputName = driver.findElement(By.xpath("//input[@id='fb_name']"));
            String name = "Name";
            inputName.sendKeys(name);
            WebElement inputAge = driver.findElement(By.xpath("//input[@id='fb_age']"));
            String age = "25";
            inputAge.sendKeys(age);

            WebElement checkBoxEnglish = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[3]/input[1]"));
            checkBoxEnglish.click();

            WebElement selectMale = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[4]/input[1]"));
            selectMale.click();
            Select dropdown = new Select(driver.findElement(By.id("like_us")));
            String dropDownSelect = "Ok, i guess";
            dropdown.selectByVisibleText(dropDownSelect);

            WebElement textArea = driver.findElement(By.tagName("textarea"));
            String testText = "This is a test text";
            textArea.sendKeys(testText);

//         click "Send"
            WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
            button.click();

//         click "No"
            WebElement buttonNo = driver.findElement(By.xpath("//button[contains(text(),'No')]"));
            buttonNo.click();

//         check fields are filled correctly



/* Sorry, I did not finish this and something doesn't work

            assertEquals(name, inputName.getAttribute("value"));
            assertEquals(age, inputAge.getAttribute("value"));

            WebElement checkBoxFrench = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[3]/input[2]"));
            WebElement checkBoxSpanish = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[3]/input[3]"));
            WebElement checkBoxChinese = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[3]/input[4]"));

            assertTrue(checkBoxEnglish.isSelected());
            assertFalse(checkBoxFrench.isSelected());
            assertFalse(checkBoxSpanish.isSelected());
            assertFalse(checkBoxChinese.isSelected());

            WebElement selectFemale = driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/form[1]/div[4]/input[2]"));

            assertTrue(selectMale.isSelected());
            assertFalse(selectFemale.isSelected());

            Select dropdown = new Select(driver.findElement(By.id("like_us")));
            String dropDownSelect = "Ok, i guess";
            dropdown.selectByVisibleText(dropDownSelect);

            assertEquals(testText, textArea.getText());
*/
        }
    }

