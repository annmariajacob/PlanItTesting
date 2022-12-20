package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ContactPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }
    @FindBy(id ="forename")
    private WebElement forNameTextBox;
    @FindBy(id ="surname")
    private WebElement surNameTextBox;
    @FindBy(id ="email")
    private WebElement emailTextBox;
    @FindBy(id ="telephone")
    private WebElement telephoneTextBox;
    @FindBy(id ="message")
    private WebElement messageTextBox;
    @FindBy(xpath ="//a[contains(text(), 'Submit')]")
    private WebElement submitButton;
    @FindBy(id ="forename-err")
    private WebElement foreNameError;
    @FindBy(id ="email-err")
    private WebElement emailError;
    @FindBy(id ="message-err")
    private WebElement messageError;
    @FindBy(xpath ="/html/body/div[2]/div/div")
    private WebElement successMessage;

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
    }

    public Boolean validateForNameError(){
        try{
            //wait.until(ExpectedConditions.visibilityOf(foreNameError));
            return foreNameError.getText().equals("Forename is required");
        }catch (NoSuchElementException e){
            return false;
        }

    }

    public Boolean validateEmailError(){
        try{
            //wait.until(ExpectedConditions.visibilityOf(emailError));
            return emailError.getText().equals("Email is required");
        }catch (NoSuchElementException e){
            return false;
        }

    }

    public Boolean validateMessageError(){
        try{
            //wait.until(ExpectedConditions.visibilityOf(messageError));
            return messageError.getText().equals("Message is required");
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void enterForName(String forName){
        forNameTextBox.sendKeys(forName);
    }

    public void enterEmail(String email){
        emailTextBox.sendKeys(email);
    }

    public void enterMessage(String message){
        messageTextBox.sendKeys(message);
    }

    public void validateSuccessMessage(){
        System.out.println(successMessage.isDisplayed());
    }

}
