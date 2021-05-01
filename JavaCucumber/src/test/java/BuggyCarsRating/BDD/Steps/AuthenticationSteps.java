package BuggyCarsRating.BDD.Steps;

import BuggyCarsRating.Model.Pages.Data.LoginData;
import BuggyCarsRating.Model.Pages.HomePage;
import BuggyCarsRating.Model.Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class AuthenticationSteps extends BaseSteps{

    public AuthenticationSteps(ObjectContainer container) {
        super(container);
    }

    @Given("a user with valid credentials")
    public void aUserWithValidCredentials() {
        container.register("loginData", new LoginData().WithUsername(System.getenv("SELENIUM_USERNAME")).WithPassword(System.getenv("SELENIUM_PASSWORD")));
    }

    @When("the user logs in to the application")
    public void theUserLogsInToTheApplication() {
        LoginData loginData = container.retrieve("loginData");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.SetLoginAndEnter(loginData.Username, loginData.Password);
    }

    @Then("the user should be successfully logged in")
    public void theUserShouldBeSuccessfullyLoggedIn() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue("Login was successful.", homePage.IsHeaderPresent());
    }

    @Given("a user with invalid credentials")
    public void aUserWithInvalidCredentials() {
        container.register("loginData", new LoginData().WithUsername("username").WithPassword("password"));
    }

    @Then("the user should be not be able to login successfully")
    public void theUserShouldBeNotBeAbleToLoginSuccessfully() {
        HomePage homePage = new HomePage(driver);
       Assert.assertTrue("Login error message displayed", homePage.LoginErrorMessageDisplayed());
    }


    @Given("I am logged in")
    public void iAmLoggedIn() {
        var username = System.getenv("SELENIUM_USERNAME");
        var password = System.getenv("SELENIUM_PASSWORD");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.SetLoginAndEnter(username, password);
    }

    @Then("I click the log off link")
    public void iClickTheLogOffLink() {
        HomePage home = new HomePage(driver);
        home.ClickLogoutButton();
    }

    @Then("I should be logged off")
    public void iShouldBeLoggedOff() {
        LoginPage login = new LoginPage(driver);

        Assert.assertTrue("login:username field is displayed", login.UsernameField().isDisplayed());
        Assert.assertTrue("login:password field is displayed", login.PasswordField().isDisplayed());
    }
}
