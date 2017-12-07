package com.mytaxi.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ZalandoShoppingCart{

    WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "z-coast-fjord_header")
    WebElement cartHeader;

    public static ZalandoShoppingCart create(WebDriver driver) {
        ZalandoShoppingCart zalandoshoppingCart = new ZalandoShoppingCart(driver);
        PageFactory.initElements(driver, zalandoshoppingCart);
        return zalandoshoppingCart;
    }


    private ZalandoShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    public int getNumberOfElementsInCart() {
        try {
            WebElement element = driver.findElement(By.className("z-coast-ui-aligner_wrapper"));
            String text = element.getText();
            if (!(text.compareTo("Llena tu cesta de nuevas tendencias") == 0)) {
                throw new Exception("Error text:" + text);
            }
        } catch (Exception e) {
            return getNumberOfObjectsInShoppingCart();
        }
        return 0;
    }

    public int getNumberOfObjectsInShoppingCart() {
        String shoppingCartMessage = "";
        List<WebElement> foundTextElements = cartHeader.findElements(By.className("z-text"));
        if (foundTextElements.size() > 1) {
            shoppingCartMessage = foundTextElements.get(1).getText();
        } else {
            shoppingCartMessage = foundTextElements.get(0).getText();
        }
        shoppingCartMessage = shoppingCartMessage.substring(7, 8);
        return Integer.parseInt(shoppingCartMessage);
    }

    public void removeAllItems() {
        WebElement clearItemsButton = driver.findElement(By.className("z-coast-fjord_removeArticle"));
        clearItemsButton.click();
    }
}
