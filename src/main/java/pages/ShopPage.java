package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShopPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public String stuffedFrogPrice;
    public String fluffyBunnyPrice;
    public String valentineBearPrice;

    public ShopPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath ="//h4[contains(text(), 'Stuffed Frog')]/following-sibling::p/a")
    private WebElement stuffedFrogBuyButton;
    @FindBy(xpath ="//h4[contains(text(), 'Fluffy Bunny')]/following-sibling::p/a")
    private WebElement fluffyBunnyBuyButton;
    @FindBy(xpath ="//h4[contains(text(), 'Valentine Bear')]/following-sibling::p/a")
    private WebElement valentineBearBuyButton;
    @FindBy(xpath ="//h4[contains(text(), 'Stuffed Frog')]/following-sibling::p/span")
    private WebElement stuffedFrogPriceText;
    @FindBy(xpath ="//h4[contains(text(), 'Fluffy Bunny')]/following-sibling::p/span")
    private WebElement fluffyBunnyPriceText;
    @FindBy(xpath ="//h4[contains(text(), 'Valentine Bear')]/following-sibling::p/span")
    private WebElement valentineBearPriceText;
    @FindBy(xpath ="//a[@href='#/cart']")
    private WebElement cartButton;

    public void buyStuffedFrog() {
        stuffedFrogBuyButton.click();
    }

    public void buyFluffyBunny() {
        fluffyBunnyBuyButton.click();
    }

    public void buyValentineBear() {
        valentineBearBuyButton.click();
    }

    public void getPriceValues() {
        stuffedFrogPrice = stuffedFrogPriceText.getText();
        fluffyBunnyPrice = fluffyBunnyPriceText.getText();
        valentineBearPrice = valentineBearPriceText.getText();
    }
    public void clickCartButton() {
        cartButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

}
