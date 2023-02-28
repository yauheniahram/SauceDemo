import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartFlow extends BaseTest{

    @Test
    public void putProductsOnCart(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();

        //Save products names
        final String productNameFirst = "Sauce Labs Backpack";
        final String productNameSecond = "Sauce Labs Bike Light";
        final String productNameThird = "Sauce Labs Bolt T-Shirt";
        final String productNameFourth = "Sauce Labs Fleece Jacket";

        String productCardLocator = "//div[text()='%s']/ancestor::div[@class='inventory_item']";
        String addToCartButton = "//button[text()='Add to cart']";
        String addToCartButtonLocator = productCardLocator + addToCartButton;

        //Add products to Cart
        driver.findElement(By.xpath(String.format(addToCartButtonLocator, productNameFirst))).click();
        driver.findElement(By.xpath(String.format(addToCartButtonLocator, productNameSecond))).click();
        driver.findElement(By.xpath(String.format(addToCartButtonLocator, productNameThird))).click();
        driver.findElement(By.xpath(String.format(addToCartButtonLocator, productNameFourth))).click();

        //Check the number of products on the Cart icon
        WebElement itemsInCart = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        final String actualItemsInCart = itemsInCart.getText();
        final String expectedItemsInCart = "4";
        Assert.assertEquals(actualItemsInCart, expectedItemsInCart, "The number of Products are matched");

        //Save products prices
        String productPrice = "//div[@class='inventory_item_price']";
        String productPriceLocator = productCardLocator + productPrice;
        String expectedPriceForFirstProduct = driver.findElement(By.xpath(String
                .format(productPriceLocator, productNameFirst))).getText();
        String expectedPriceForSecondProduct = driver.findElement(By.xpath(String
                .format(productPriceLocator, productNameSecond))).getText();
        String expectedPriceForThirdProduct = driver.findElement(By.xpath(String
                .format(productPriceLocator, productNameThird))).getText();
        String expectedPriceForFourthProduct = driver.findElement(By.xpath(String
                .format(productPriceLocator, productNameFourth))).getText();

        //Open Cart
        driver.findElement(By.id("shopping_cart_container")).click();

        // Check the Products in the Cart
        String productInTheCart = "//div[text()='%s']/ancestor::div[@class='cart_item']";
        Assert.assertTrue(driver.findElement(By.xpath(String.format(productInTheCart, productNameFirst))).isDisplayed(),
                "Product 1 has not been added to the cart");
        Assert.assertTrue(driver.findElement(By.xpath(String.format(productInTheCart, productNameSecond))).isDisplayed(),
                "Product 2 has not been added to the cart");
        Assert.assertTrue(driver.findElement(By.xpath(String.format(productInTheCart,productNameThird))).isDisplayed(),
                "Product 3 has not been added to the cart");
        Assert.assertTrue(driver.findElement(By.xpath(String.format(productInTheCart, productNameFourth))).isDisplayed(),
                "Product 4 has not been added to the cart");

        // Check the Products in the Cart
        String productInTheCartPriceLocator = productInTheCart + "//div[@class='inventory_item_price']";
        String actualPrice1 = driver.findElement(By.xpath(String.format(productInTheCartPriceLocator,productNameFirst)))
                .getText();
        Assert.assertEquals(actualPrice1, expectedPriceForFirstProduct, "The price doesn't match");
        String actualPrice2 = driver.findElement(By.xpath(String.format(productInTheCartPriceLocator,productNameSecond)))
                .getText();
        Assert.assertEquals(actualPrice2, expectedPriceForSecondProduct, "The price doesn't match");
        String actualPrice3 = driver.findElement(By.xpath(String.format(productInTheCartPriceLocator,productNameThird)))
                .getText();
        Assert.assertEquals(actualPrice3, expectedPriceForThirdProduct, "The price doesn't match");
        String actualPrice4 = driver.findElement(By.xpath(String.format(productInTheCartPriceLocator,productNameFourth)))
                .getText();
        Assert.assertEquals(actualPrice4, expectedPriceForFourthProduct, "The price doesn't match");
    }

    @Test
    public void checkThatAddToCartButtonBecomesRemoveButtonOnTheProductsMenu(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();

        final String productName = "Sauce Labs Backpack";

        String productCardLocator = "//div[text()='%s']/ancestor::div[@class='inventory_item']";
        String addToCartButton = "//button[text()='Add to cart']";
        String addToCartButtonLocator = productCardLocator + addToCartButton;

        //Add products to Cart
        driver.findElement(By.xpath(String.format(addToCartButtonLocator, productName))).click();

        //Check that Add To Cart button becomes Remove button
        String productRemoveLocator = "//div[text()='%s']/ancestor::div[@class='inventory_item']";
        String removeToCartButton = "//button[text()='Remove']";
        String removeToCartButtonLocator = productRemoveLocator + removeToCartButton;
        Assert.assertTrue(driver.findElement(By.xpath(String.format(removeToCartButtonLocator, productName)))
                .isDisplayed());
    }

    @Test
    public void returnToProductsMenuFromCart(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();

        //Open Cart
        driver.findElement(By.id("shopping_cart_container")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn btn_secondary back btn_medium']"
                )).isDisplayed());
        WebElement cartNameMenu = driver.findElement(By.xpath(
                "//span[@class='title']"));
        final String actualCartNameMenu = cartNameMenu.getText();
        final String expectedCartNameMenu = "YOUR CART";
        Assert.assertEquals(actualCartNameMenu, expectedCartNameMenu);

        //Return to Products menu
        driver.findElement(By.xpath("//button[@class='btn btn_secondary back btn_medium']"
        )).click();
        WebElement productsNameMenu = driver.findElement(By.xpath(
                "//span[@class='title']"));
        final String actualProductsNameMenu= productsNameMenu.getText();
        final String expectedProductsNameMenu = "PRODUCTS";
        Assert.assertEquals(actualProductsNameMenu, expectedProductsNameMenu);
    }

    @Test
    public void logoutFromSaucedemo(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();

        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).
                isDisplayed(), "Menu Burger is not displayed");
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();

        driver.findElement(By.id("logout_sidebar_link")).click();

        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }
}