package BuggyCarsRating.Model.Pages;

import BuggyCarsRating.Model.Pages.Data.ProfileData;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage<ProfilePage>{

    public WebElement FirstNameField() { return driver.findElement(By.id("firstName")); }
    public WebElement LastNameField() { return driver.findElement(By.id("lastName")); }
    public WebElement GenderField() { return  driver.findElement(By.id("gender")); }
    public WebElement AgeField() { return  driver.findElement(By.id("age")); }
    public WebElement AddressField() { return  driver.findElement(By.id("address")); }
    public WebElement PhoneField() { return  driver.findElement(By.id("phone")); }
    public WebElement CurrentPasswordField() { return  driver.findElement(By.id("currentPassword")); }
    public WebElement NewPasswordField() { return  driver.findElement(By.id("newPassword")); }
    public WebElement ConfirmPasswordField() { return driver.findElement(By.id("newPasswordConfirmation")); }
    public WebElement SaveButton() { return driver.findElement(By.xpath("//button[@type='submit'][text()='Save']")); }


    public ProfilePage(WebDriver driver)
    {
        super(driver);
    }

    public boolean ProfileSaveSuccessMessageDisplayed() { return driver.findElement(By.xpath("//div[contains(text(),'The profile has been saved successful')]")).isDisplayed(); }

    public void SetGender(String gender)
    {
        GenderField().sendKeys(gender);
        GenderField().sendKeys(Keys.TAB);
    }

    public ProfileData UpdateProfileRandom(String password, String newPassword)
    {
        ProfileData data = new ProfileData();
        var address = Faker.instance().address().secondaryAddress();
        var num = Faker.instance().number().numberBetween(1, 1000000000);
        var phone = Integer.toString(num);

        SetGender("Female");
        AgeField().sendKeys("21");
        AddressField().sendKeys(address);
        PhoneField().sendKeys(phone);
        CurrentPasswordField().sendKeys(password);
        NewPasswordField().sendKeys(newPassword);
        ConfirmPasswordField().sendKeys(newPassword);

        SaveButton().click();

        return data.WithFirstName(FirstNameField().getAttribute("value")).WithLastName(LastNameField().getAttribute("value")).
                WithGender("Female").WithAge("21").WithAddress(address).WithPhone(phone);
    }

    public ProfileData GetCurrentProfileInformation()
    {
        ProfileData data = new ProfileData();

        return data.WithFirstName(FirstNameField().getAttribute("value")).WithLastName(LastNameField().getAttribute("value"))
                .WithGender(GenderField().getAttribute("value")).WithAge(AgeField().getAttribute("value")).WithAddress(AddressField().getAttribute("value"))
                .WithPhone(PhoneField().getAttribute("value"));
    }
}
