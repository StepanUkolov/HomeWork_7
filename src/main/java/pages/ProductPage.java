package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {


    @FindBy(xpath = "//span[@class='current-price-value']")
    public WebElement price;

    @FindBy(xpath = "//select[@class='form-control select']")
    public WebElement garantySelect;

    @FindBy(xpath = "//span[contains(text(),'Купить')]/..")
    public WebElement buyBtn;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
