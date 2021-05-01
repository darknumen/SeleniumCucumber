package BuggyCarsRating.BDD.Steps;

import BuggyCarsRating.Model.Pages.Data.LoginData;
import BuggyCarsRating.Model.Pages.Data.ProfileData;
import BuggyCarsRating.Model.Pages.*;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SiteFunctionalitySteps extends BaseSteps{

    public SiteFunctionalitySteps(ObjectContainer container) {
        super(container);
    }

    @Given("I am a registered new user and logged in")
    public void iAmARegisteredNewUserAndLoggedIn() {
        HomePage home = new HomePage(driver);
        home.ClickRegisterButton();

        String username = Faker.instance().name().username();
        String password = "Test012345!";


        RegistrationPage register = new RegistrationPage(driver);
        register.CreateNewUser(username, password);

        container.register("createdNewUser", new LoginData().WithUsername(username).WithPassword(password));

        LoginPage login = new LoginPage(driver);
        login.SetLoginAndEnter(username, password);

        home.ClickBuggyRatingLink();
    }

    @When("I update my profile and password")
    public void iUpdateMyProfileAndPassword() {

        HomePage home = new HomePage(driver);
        home.ClickProfileButton();

        ProfilePage profile = new ProfilePage(driver);
        ProfileData data = new ProfileData();

        LoginData login = container.retrieve("createdNewUser");

        String newPassword = "Test01234567!";
        data = profile.UpdateProfileRandom(login.Password, newPassword);

        Assert.assertTrue("save successfully displayed", profile.ProfileSaveSuccessMessageDisplayed());

        container.register("updatedNewUser", new LoginData().WithUsername(login.Username).WithPassword(newPassword));
        container.register("profileData", data);
    }

    @Then("I login using new password and verify profile change was saved")
    public void iLoginUsingNewPasswordAndVerifyProfileChangeWasSaved() {

        HomePage home = new HomePage(driver);
        home.ClickLogoutButton();

        LoginData credentials = container.retrieve("updatedNewUser");

        LoginPage login = new LoginPage(driver);
        login.SetLoginAndEnter(credentials.Username, credentials.Password);

        home.ClickProfileButton();

        ProfileData profileSaved = container.retrieve("profileData");

        ProfilePage profilepage = new ProfilePage(driver);
        ProfileData profileCurrent = profilepage.GetCurrentProfileInformation();

        Assert.assertTrue("Saved profile and current profile should be the same" ,profileSaved.equals(profileCurrent));
    }

    @When("I navigate to the most popular model")
    public void iNavigateToTheMostPopularModel() {

        HomePage home = new HomePage(driver);
        home.ClickPopularModelLink();
    }

    @And("I made a comment to vote")
    public void iMadeACommentToVote() {

        ModelPage model = new ModelPage(driver);
        container.register("voteCount", model.GetVoteNumber());

        model.SetCommentField(Faker.instance().company().catchPhrase());
        model.ClickVoteButton();
    }

    @Then("check that vote was counted and comment could not be made anymore")
    public void checkThatVoteWasCountedAndCommentCouldNotBeMadeAnymore() {
        ModelPage model = new ModelPage(driver);
        Assert.assertEquals("Vote has been counted" ,model.GetVoteMessageDoneText(), "Thank you for your vote!");

        int voteAfter = Integer.parseInt(model.GetVoteNumber());
        int voteBefore = Integer.parseInt(container.retrieve("voteCount"));

        Assert.assertEquals("Added vote should be counted", voteAfter, voteBefore + 1);
    }
}
