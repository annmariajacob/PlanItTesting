package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath ="//*[@id=\"nav-contact\"]/a")
    private WebElement contactMenu;

    @FindBy(xpath ="//a[contains(text(), 'Start Shopping »')]")
    private WebElement shopButton;

    public void clickContactMenu() {
        wait.until(ExpectedConditions.visibilityOf(contactMenu));
        contactMenu.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }
    public void clickShopButton(){
        wait.until(ExpectedConditions.visibilityOf(shopButton));
        shopButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }

}
