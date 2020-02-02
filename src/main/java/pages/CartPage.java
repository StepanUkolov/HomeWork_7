package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'cart-items__product-thumbnail')]")
    public WebElement product;

    @FindBy(xpath = "//span[contains(text(),'Вернуть удалённый товар')]")
    public WebElement returnDeletedProduct;

    public WebElement getGaranty(String value) {
        WebElement curentGaranty = product.findElement(By.xpath("//a[contains(text(),'" + value + "')]/../..//span"));
        return curentGaranty;
    }

    public WebElement getPrice(String value) {
        WebElement curentPrice = product.findElement(By.xpath("//a[contains(text(),'" + value + "')]/../../../..//span[@class='price__current']"));
        return curentPrice;
    }

    public WebElement deleteBtn(String value) {
        WebElement curentBtn = product.findElement(By.xpath("//a[contains(text(),'" + value + "')]/../..//button[contains(text(),'Удалить')]"));
        return curentBtn;
    }

    public WebElement plusBtn(String value) {
        WebElement curentBtn = product.findElement(By.xpath("//a[contains(text(),'" + value + "')]/../../../..//button[contains(@class,'button_plus')]"));
        return curentBtn;
    }

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
