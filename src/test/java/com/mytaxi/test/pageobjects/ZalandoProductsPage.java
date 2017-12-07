package com.mytaxi.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ZalandoProductsPage{

    @FindBy(how = How.CLASS_NAME, using = "z-nvg-cognac_articles")
    WebElement productGrid;

    WebDriver driver;

    public static ZalandoProductsPage create(WebDriver driver) {
        ZalandoProductsPage zalandoProductsPage = new ZalandoProductsPage(driver);
        PageFactory.initElements(driver, zalandoProductsPage);
        return zalandoProductsPage;
    }


    private ZalandoProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFirstItem() {
        List<WebElement> products = productGrid.findElements(By.xpath("./*"));
        WebElement product = products.get(0);
        product.findElement(By.xpath("./*")).click();
    }
}
