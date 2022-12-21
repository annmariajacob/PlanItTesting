import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ContactPage;
import pages.HomePage;
import pages.ShopPage;

import java.time.Duration;

public class ShopPageDetails {

    WebDriver driver = null;

    @BeforeMethod(alwaysRun = true)
    public void setDriver(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }
    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

    @Test(enabled = true)
    public void addToCartAndValidate() {
        WebDriver testDriver = driver;
        driver.get("https://jupiter.cloud.planittesting.com/");
        HomePage homePage = new HomePage(driver);
        homePage.clickShopButton();

        ShopPage shopPage = new ShopPage(driver);
        shopPage.buyStuffedFrog();
        shopPage.buyFluffyBunny();
        shopPage.buyValentineBear();
        shopPage.getPriceValues();
        shopPage.clickCartButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.setQuantityValidatePrice("Stuffed Frog", shopPage.stuffedFrogPrice, "2"), "Incorrect price/subtotal for Stuffed Frog");
        Assert.assertTrue(cartPage.setQuantityValidatePrice("Fluffy Bunny", shopPage.fluffyBunnyPrice, "5"), "Incorrect price/subtotal for Fluffy Bunny");
        Assert.assertTrue(cartPage.setQuantityValidatePrice("Valentine Bear", shopPage.valentineBearPrice, "3"), "Incorrect price/subtotal for Valentine Bear");

        Assert.assertTrue(cartPage.validateTotal(), "Incorrect total");
    }
}
