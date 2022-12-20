import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;

public class HomePageDetails {

WebDriver driver = null;

@BeforeMethod(alwaysRun = true)
    public void setDriver(){
    System.setProperty("webdriver.chrome.driver", "/Users/annmariajacob/Downloads/chromedriver");
    driver = new ChromeDriver();
}
@AfterMethod(alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

@Test(enabled = true)
    public void validateContactPageErrorMessage(){
    WebDriver testDriver = driver;
    driver.get("https://jupiter.cloud.planittesting.com/");
    HomePage homePage = new HomePage(driver);
    homePage.clickContactMenu();

    ContactPage contactPage = new ContactPage(driver);
    contactPage.clickSubmitButton();
    Assert.assertTrue(contactPage.validateForNameError(), "Error message not present/incorrect for forName field");
    Assert.assertTrue(contactPage.validateEmailError(), "Error message not present/incorrect for Email field");
    Assert.assertTrue(contactPage.validateMessageError(), "Error message not present/incorrect for Message field");

    contactPage.enterForName("John");
    contactPage.enterEmail("JohnJohn@gmail.com");
    contactPage.enterMessage("This message is for testing purpose");

    Assert.assertFalse(contactPage.validateForNameError(), "Error message still present for forName field");
    Assert.assertFalse(contactPage.validateEmailError(), "Error message still present for Email field");
    Assert.assertFalse(contactPage.validateMessageError(), "Error message still present for Message field");

}
@Test(enabled = true, invocationCount = 5)
    public void validateContactPage(){
        WebDriver testDriver = driver;
        driver.get("https://jupiter.cloud.planittesting.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickContactMenu();

        ContactPage contactPage = new ContactPage(driver);
        contactPage.enterForName("John");
        contactPage.enterEmail("JohnJohn@gmail.com");
        contactPage.enterMessage("This message is for testing purpose");
        contactPage.clickSubmitButton();
        contactPage.validateSuccessMessage();

    }

}
