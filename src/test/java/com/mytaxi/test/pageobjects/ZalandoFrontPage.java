package com.mytaxi.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ZalandoFrontPage{

    private static final String ZALANDO_URL = "https://www.zalando.es";

    @FindBy(how = How.ID, using = "customerAccountBox")
    private WebElement customerAccountBox;

    @FindBy(how = How.CLASS_NAME, using = "cartWrapper")
    private WebElement shopingCart;

    @FindBy(how = How.CLASS_NAME, using = "z-navicat-header_categories")
    private WebElement headerCategorias;

    @FindBy(how = How.CLASS_NAME, using = "z-navicat-header_subcategoryColumns")
    private WebElement subcategoryColumns;


    protected WebDriver driver;

    protected ZalandoFrontPage(WebDriver driver) {
        this.driver = driver;
    }

    public static ZalandoFrontPage create(WebDriver driver) {
        ZalandoFrontPage zalandoFrontPage = new ZalandoFrontPage(driver);
        PageFactory.initElements(driver, zalandoFrontPage);
        return zalandoFrontPage;
    }


    public void open() {
        driver.get(ZALANDO_URL);
        driver.manage().window().maximize();
    }

    public ZalandoShoppingCart openShoppingCart() {
        driver.get(ZALANDO_URL + "/cart");
        //shopingCart.click();
        return (ZalandoShoppingCart)ZalandoShoppingCart.create(driver);
    }

    public ZalandoLoginPage clickLoginBox() {
        customerAccountBox.click();
        return (ZalandoLoginPage)ZalandoLoginPage.create(driver);
    }

    public void section(String category, String brand) throws Exception {
        Thread.sleep(600);
        List<WebElement> elements = headerCategorias.findElements(By.xpath("./*"));
        boolean found = false;
        for (WebElement element : elements) {
            WebElement labelChild = element.findElement(By.className("z-navicat-header_categoryLabel"));
            String label = labelChild.getText();
            if (label.compareTo(category) == 0) {
                Actions action = new Actions(driver);
                action.moveToElement(element).perform();
                found = true;
                clickSubsection(element, brand);
                return;
            }
        }
        if (!found) {
            throw new Exception("Section " + category + " does not exists");
        }
    }

    private void clickSubsection(WebElement subColums, String brand) throws Exception {
        Thread.sleep(600);
        List<WebElement> items = subColums.findElements(By.className("z-navicat-header_subcategoryLinkWrapper"));
        boolean found = false;
        for (WebElement item : items) {
            if (item.getText().compareTo(brand) == 0) {
                item.click();
                found = true;
                return;
            }
        }
        if (!found) {
            throw new Exception("Brand " + brand + " does not exists");
        }
    }


}
