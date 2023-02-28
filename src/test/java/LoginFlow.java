import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFlow extends BaseTest{

    @Test
    public void loginByStandardUser(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).
                isDisplayed(), "Menu Burger is displayed");
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Products']")).
                isDisplayed(), "Title is displayed");
    }

    @Test
    public void loginWithoutCredentials(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("login-button")).submit();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='error-message-container error']")).isDisplayed(),
                "Error message section is displayed");
        WebElement errorText = driver.findElement(By.xpath("//h3[@data-test='error']"));
        final String actualError = errorText.getText();
        final String expectedError = "Epic sadface: Username is required";
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void loginByLockedOutUser(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='error-message-container error']")).isDisplayed(),
                "Error message section is displayed");
        WebElement errorText = driver.findElement(By.xpath("//h3[@data-test='error']"));
        final String actualError = errorText.getText();
        final String expectedError = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void loginByProblemUser(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).
                isDisplayed(), "Menu Burger is displayed");
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Products']")).
                isDisplayed(), "Title is displayed");
        WebElement attribute = driver.findElement(By.xpath("//img[@class='inventory_item_img']"));
        final String actualAttribute = attribute.getAttribute("src");
        final String expectedAttribute = "https://www.saucedemo.com/static/media/sauce-backpack-1200x1500.34e7aa42.jpg";
        Assert.assertEquals(actualAttribute, expectedAttribute, "Failed because the Attributes are different");
    }

    @Test
    public void loginByPerformanceGlitchUserUser(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).submit();
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).
                isDisplayed(), "Menu Burger is displayed");
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Products']")).
                isDisplayed(), "Title is displayed");
    }
}