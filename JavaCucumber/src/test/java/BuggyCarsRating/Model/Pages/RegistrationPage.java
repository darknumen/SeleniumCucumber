package BuggyCarsRating.Model.Pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage<RegistrationPage>{

    public void SetLoginField(String login) { driver.findElement(By.id("username")).sendKeys(login);  }

    public void SetFirstNameField(String firstName) { driver.findElement(By.id("firstName")).sendKeys(firstName); }

    public void SetLastNameField(String lastName) { driver.findElement(By.id("lastName")).sendKeys(lastName); }

    public void SetPasswordField(String password) { driver.findElement(By.id("password")).sendKeys(password); }

    public void SetConfirmPasswordField(String password) { driver.findElement(By.id("confirmPassword")).sendKeys(password); }

    public void ClickRegisterButton() { driver.findElement(By.xpath("//button[@type='submit'][text()='Register']")).click(); }

    public boolean RegistrationSuccessMessageDisplayed() { return driver.findElement(By.xpath("//div[contains(text(),'Registration is successful')]")).isDisplayed(); }

    public RegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    public void CreateNewUser(String user, String password)
    {
        SetLoginField(user);
        SetFirstNameField(Faker.instance().name().firstName());
        SetLastNameField(Faker.instance().name().lastName());
        SetPasswordField(password);
        SetConfirmPasswordField(password);

        ClickRegisterButton();
    }
}
