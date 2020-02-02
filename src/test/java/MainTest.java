import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest extends BaseTest {

    @Test
    public void cuTest() {

        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 60);


        Product product1 = new Product(properties.getProperty("product1"));
        Product product2 = new Product(properties.getProperty("product2"));

        driver.navigate().to(properties.getProperty("app.url"));
/** ------------------------------------------------------------------------------ */
        //Вбиваем playstaition в поиск
        wait.until(ExpectedConditions.visibilityOf(mainPage.searchField));
        fillField(mainPage.searchField, product1.getName() + Keys.ENTER);

        //Выбираем товар по ключу PlayStation 4 Slim Black
        mainPage.searchKey(properties.getProperty("product1key"));

        //Цена без гарантии
        product1.setPrice(Integer.parseInt(productPage.price.getText().replaceAll(" ", "")));

        //Выбираем гарантию 2 года
        wait.until(ExpectedConditions.elementToBeClickable(productPage.garantySelect));
        Select select = new Select(productPage.garantySelect);
        select.selectByValue("2");

        //Гарантия
        product1.setGaranty(select.getFirstSelectedOption().getText().replaceAll(" ", ""));

        //Цена с гарантией
        product1.setGarantyPrice(Integer.parseInt(productPage.price.getText().replaceAll(" ", "")));

        //Добавить в корзину
        wait.until(ExpectedConditions.elementToBeClickable(productPage.buyBtn));
        productPage.buyBtn.click();


/** ------------------------------------------------------------------------------ */
        //Вбиваем playstaition в поиск
        wait.until(ExpectedConditions.visibilityOf(mainPage.searchField));
        fillField(mainPage.searchField, product2.getName() + Keys.ENTER);

        //Цена без гарантии
        product2.setPrice(Integer.parseInt(productPage.price.getText().replaceAll(" ", "")));

        //Добавить в корзину
        wait.until(ExpectedConditions.elementToBeClickable(productPage.buyBtn));
        productPage.buyBtn.click();

/** ------------------------------------------------------------------------------ */
        //Ждем 3 секунды для того, чтобы цена корзины успела поменяться
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Цена корзины
        int cartPrice = Integer.parseInt(mainPage.goToCart.getText().replaceAll(" ", ""));

        //Проверяем, что цена корзины совпадает
        Assert.assertEquals(product1.getGarantyPrice() + product2.getPrice(), cartPrice);

        //Переходим в корзину
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.goToCart));
        mainPage.goToCart.click();

        //Проверяем, что выбрана гарантия 2 года
        Assert.assertEquals("Продленная гарантия на 24 мес. (+4 680)", cartPage.getGaranty(properties.getProperty("product1key")).getText());

        //Цена первого товара
        int price1 = Integer.parseInt(cartPage.getPrice(properties.getProperty("product1key")).getText().replaceAll(" ", ""));

        //Цена второго товара
        int price2 = Integer.parseInt(cartPage.getPrice(properties.getProperty("product2key")).getText().replaceAll(" ", ""));

        //Ищем по регулярке (+4 680)
        Pattern pattern = Pattern.compile("\\(\\+.+\\)");
        Matcher matcher = pattern.matcher(cartPage.getGaranty(properties.getProperty("product1key")).getText());
        String garantyPrice = "";
        while (matcher.find()) {
            garantyPrice = matcher.group();
        }

        //Цена гарантии 4680
        int price3 = Integer.parseInt(garantyPrice.replaceAll("[\\D]", ""));

        //Проверяем, что цена корзины совпадает
        Assert.assertEquals(price1 + price2 + price3, cartPrice);

/** ------------------------------------------------------------------------------ */

        //Удаляем второй товар из корзины
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.deleteBtn(properties.getProperty("product2key"))));
        cartPage.deleteBtn(properties.getProperty("product2key")).click();

        //Ждем 3 секунды для того, чтобы цена корзины успела поменяться
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Обновленная цена корзины
        cartPrice = Integer.parseInt(mainPage.goToCart.getText().replaceAll(" ", ""));

        //Проверяем, что цена корзины совпадает
        Assert.assertEquals(price1 + price3, cartPrice);

        //Добавляем два первых товара (тут тоже надо подождать принудительно, иначе не нажимается)
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.plusBtn(properties.getProperty("product1key"))));
        cartPage.plusBtn(properties.getProperty("product1key")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.plusBtn(properties.getProperty("product1key"))));
        cartPage.plusBtn(properties.getProperty("product1key")).click();

        //Ждем 3 секунды для того, чтобы цена корзины успела поменяться
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Обновленная цена корзины
        cartPrice = Integer.parseInt(mainPage.goToCart.getText().replaceAll(" ", ""));

        //Проверяем, что цена корзины совпадает
        Assert.assertEquals(price1 * 3 + price3 * 3, cartPrice);

/** ------------------------------------------------------------------------------ */

        //Возвращаем удаленный товар
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.returnDeletedProduct));
        cartPage.returnDeletedProduct.click();

        //Ждем 3 секунды для того, чтобы цена корзины успела поменяться
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Обновленная цена корзины
        cartPrice = Integer.parseInt(mainPage.goToCart.getText().replaceAll(" ", ""));

        //Проверяем, что цена корзины совпадает
        Assert.assertEquals(price1 * 3 + price3 * 3 + price2, cartPrice);

    }
}

