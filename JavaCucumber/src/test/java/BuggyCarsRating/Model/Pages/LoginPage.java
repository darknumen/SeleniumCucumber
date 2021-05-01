package BuggyCarsRating.Model.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage<HomePage> {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void SetLoginAndEnter(String user, String password)
    {
        UsernameField().sendKeys(user);
        PasswordField().sendKeys(password);
        ClickSubmitButton();
    }

    public WebElement UsernameField(){
        return driver.findElement(By.name("login"));
    }

    public WebElement PasswordField(){
        return driver.findElement(By.name("password"));
    }

    public void ClickSubmitButton(){
        driver.findElement(By.xpath("//button[@type='submit'][text()='Login']")).click();
    }
}
