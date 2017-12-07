package com.mytaxi.test.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ZalandoLoginPage {

    @FindBy(how = How.ID, using = "loginEmail")
    WebElement loginInput;
    @FindBy(how = How.ID, using = "loginPassword")
    WebElement loginPassword;
    @FindBy(how = How.ID, using = "login")
    WebElement loginButton;

    private WebDriver driver;

    public static ZalandoLoginPage create(WebDriver driver) {
        ZalandoLoginPage zalandoLoginPage = new ZalandoLoginPage(driver);
        PageFactory.initElements(driver, zalandoLoginPage);
        return zalandoLoginPage;
    }


    private ZalandoLoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void makeLogin(String user, String password) {
        loginInput.sendKeys(user);
        loginPassword.sendKeys(password);
        loginButton.click();
    }
}
