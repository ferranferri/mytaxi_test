package com.mytaxi.test.zalandocarttest;

import com.mytaxi.test.pageobjects.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class AddItemsToCart {
    WebDriver driver;
    private ZalandoFrontPage zalandoFrontPage;

    @Given("^A browser is open$")
    public void aBrowserIsOpen() throws Throwable {

        driver = ChromeDriverHelper.getDriver();
    }

    @And("^Url is loaded$")
    public void HomeUrlLoaded() throws Throwable {
        zalandoFrontPage = ZalandoFrontPage.create(driver);
        zalandoFrontPage.open();
    }

    @And("^Shopping cart is empty$")
    public void shoppingCartIsEmpty() throws Throwable {
        ZalandoShoppingCart zalandoShoppingCart = zalandoFrontPage.openShoppingCart();
        if (zalandoShoppingCart.getNumberOfElementsInCart() > 0) {
            zalandoShoppingCart.removeAllItems();
            Assert.assertEquals(0, zalandoShoppingCart.getNumberOfElementsInCart());
        }
    }

    @Given("^Log in with user: \"([^\"]*)\" and password: \"([^\"]*)\"$")
    public void logInUser(String user, String password) throws Throwable {
        zalandoFrontPage = ZalandoFrontPage.create(driver);
        zalandoFrontPage.open();
        ZalandoLoginPage zalandoLoginPage = zalandoFrontPage.clickLoginBox();
        zalandoLoginPage.makeLogin(user, password);
    }

    @Then("^Go to main page$")
    public void go_to_main_page() throws Throwable {
       HomeUrlLoaded();
    }


    @And("^Select \"([^\"]*)\" and brand \"([^\"]*)\"$")
    public void selectAndBrand(String category, String brand) throws Throwable {
        zalandoFrontPage.section(category, brand);
    }


    @And("^Click first product$")
    public void clickFirstProduct() throws Throwable {
        ZalandoProductsPage zalandoProductsPage = ZalandoProductsPage.create(driver);
        zalandoProductsPage.clickOnFirstItem();
    }

    @And("^Select size and Add to cart$")
    public void selectSize() throws Throwable {
        ZalandoProductPage zalandoProductPage = ZalandoProductPage.create(driver);
        zalandoProductPage.chooseSize();
    }

    @Then("^Go to shopping cart and check that number of items are (\\d+)$")
    public void goToShoppingCartAndCheckThatNumberOfItemsAre(int numberOfItems) throws Throwable {
        HomeUrlLoaded();
        zalandoFrontPage = ZalandoFrontPage.create(driver);
        zalandoFrontPage.openShoppingCart();
        ZalandoShoppingCart zalandoShoppingCart = ZalandoShoppingCart.create(driver);
        Assert.assertEquals(2,zalandoShoppingCart.getNumberOfElementsInCart());


    }
}
