package BuggyCarsRating.BDD.Steps;

import BuggyCarsRating.Model.Pages.Data.LoginData;
import BuggyCarsRating.Model.Pages.HomePage;
import BuggyCarsRating.Model.Pages.LoginPage;
import BuggyCarsRating.Model.Pages.RegistrationPage;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class RegistrationSteps extends BaseSteps{

    public RegistrationSteps(ObjectContainer container) {
        super(container);
    }

    @Given("that I register on the site using:")
    public void thatIRegisterOnTheSiteUsing(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        HomePage home = new HomePage(driver);
        home.ClickRegisterButton();

        var username = Faker.instance().name().username();
        var password = data.get(0).get("Password");
        container.register("loginCredentials", new LoginData().WithUsername(username).WithPassword(password));

        RegistrationPage register = new RegistrationPage(driver);
        register.CreateNewUser(username, password);
    }

    @Then("verify registration is a success")
    public void verifyRegistrationIsASuccess() {
        RegistrationPage register = new RegistrationPage(driver);
        Assert.assertTrue("registration message successful shown", register.RegistrationSuccessMessageDisplayed());
    }

    @Then("use created credentials to log in successfully")
    public void useCreatedCredentialsToLogInSuccessfully() {
        LoginData loginData = container.retrieve("loginCredentials");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.SetLoginAndEnter(loginData.Username, loginData.Password);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue("Login was successful.", homePage.IsHeaderPresent());
    }
}
