package com.mytaxi.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ZalandoProductPage {
    WebDriver driver;

    @FindBy(how = How.ID, using = "z-pdp-topSection-addToCartButton")
    WebElement addToCartButton;

    public static ZalandoProductPage create(WebDriver driver) {
        ZalandoProductPage zalandoProductPage = new ZalandoProductPage(driver);
        PageFactory.initElements(driver, zalandoProductPage);
        return zalandoProductPage;
    }


    private ZalandoProductPage(WebDriver driver) {
        this.driver = driver;
    }


    public void chooseSize() {
        addToCartButton.click();
        WebElement chooserGrid = driver.findElement(By.className("h-size-table-option-wrapper"));
        List<WebElement> messageColumns = chooserGrid.findElements(By.className("h-col-12"));
        for (WebElement messageColumn : messageColumns) {
            try {
                WebElement possibleEmptyElement = messageColumn.findElement(By.xpath("./*"));
            } catch (Exception e) {
                messageColumn.click();
                return;
            }
        }
    }
}
