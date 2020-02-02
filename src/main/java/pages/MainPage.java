package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    @FindBy(xpath = "//nav//div[@class='ui-input-search ui-input-search_presearch']/input")
    public WebElement searchField;

    @FindBy(xpath = "//div[@class='catalog-item']")
    public WebElement products;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    public WebElement goToCart;

    public void searchKey(String value) {
        WebElement curent = products.findElement(By.xpath("//a[contains(text(),'" + value + "')]"));
        //Проверка на то, что кнопка Купить есть
        if (curent.findElement(By.xpath("//../../../../..//button[contains(text(),'Купить')]")).isEnabled()) {
            curent.click();
        } else System.out.println("Кнопка Купить отсутствует");
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
