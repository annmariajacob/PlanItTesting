package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    double [] subTotalArr = new double[3];

    public CartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="/html/body/div[2]/div/form/table/tbody")
    private WebElement cartTable;
    @FindBy(xpath ="/html/body/div[2]/div/form/table/tbody/tr[1]/td[3]/input")
    private WebElement stuffedFrogQuantity;

    public void setQuantity(String quantity){
        stuffedFrogQuantity.sendKeys(quantity);

    }

    public Boolean setQuantityValidatePrice(String item, String pricePerItem, String quantity){
        List<WebElement> rows = cartTable.findElements(By.tagName("tr"));
        boolean pricePerItemFlag = false;
        boolean subTotalFlag = false;
        //double [] subTotalArr = new double[rows.size()];

        for (int i=1; i<=rows.size(); i++){
            if(driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+i+"]/td[1]")).getText().equals(item)){
                driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+i+"]/td[3]/input")).clear();
                driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+i+"]/td[3]/input")).sendKeys(quantity);
                if (driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+i+"]/td[2]")).getText().equals(pricePerItem)){
                    pricePerItemFlag = true;
                    double price = Double.parseDouble(driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+i+"]/td[2]")).getText().replaceAll("\\$", ""));
                    double subtotal = Double.parseDouble(driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+i+"]/td[4]")).getText().replaceAll("\\$", ""));
                    subTotalArr[i-1]=subtotal;
                    if(price*Integer.parseInt(quantity) == subtotal){
                        subTotalFlag = true;
                    }
                    break;
                }
                else
                    break;
            }
        }

        return pricePerItemFlag && subTotalFlag;
    }

    public Boolean validateTotal(){
        double totalExpected = 0.0;
        boolean totalFlag = false;
        for (int i=0 ; i<subTotalArr.length; i++){
            totalExpected = totalExpected + subTotalArr[i];
        }
        double totalActual = Double.parseDouble(driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[1]/td/strong")).getText().split(": ")[1]);
        if (totalActual == totalExpected){
            totalFlag = true;
        }
        return totalFlag;
    }


}
