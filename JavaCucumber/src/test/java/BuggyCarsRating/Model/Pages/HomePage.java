package BuggyCarsRating.Model.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage<HomePage> {

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public boolean IsHeaderPresent()
    {
        try
        {
            return driver.findElement(By.xpath("//span[contains(text(),'Hi')]")).isDisplayed();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean LoginErrorMessageDisplayed(){
        return driver.findElement(By.xpath("//span[contains(text(), 'Invalid username/password')]")).isDisplayed();
    }

    public void ClickLogoutButton(){
        driver.findElement(By.xpath("//a[@class='nav-link'][text()='Logout']")).click();
    }

    public void ClickRegisterButton() { driver.findElement(By.xpath("//a[@class='btn btn-success-outline'][text()='Register']")).click(); }

    public void ClickProfileButton() { driver.findElement(By.xpath("//a[@class='nav-link'][text()='Profile']")).click(); }

    public void ClickPopularModelLink() { driver.findElement(By.xpath("//a[contains(@href,'/model/')]")).click(); }

    public String GetPopularModelNameText() { return driver.findElement(By.cssSelector("div:nth-child(2) > div > div > h3")).getText(); }

    public void ClickOverallLink() { driver.findElement(By.xpath("//a[contains(@href,'/overall')]")); }
}
