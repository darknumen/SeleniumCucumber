package BuggyCarsRating.BDD.Steps;

import BuggyCarsRating.Model.Pages.HomePage;
import BuggyCarsRating.Model.Pages.ModelPage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class SiteNavigationSteps extends BaseSteps{

    public SiteNavigationSteps(ObjectContainer container) {
        super(container);
    }

    @Then("I navigate to Popular Model then confirm model page and back to main page successfully")
    public void iNavigateToPopularModelThenConfirmModelPageAndBackToMainPageSuccessfully() {

        HomePage home = new HomePage(driver);

        String popularModel = home.GetPopularModelNameText();
        home.ClickPopularModelLink();

        ModelPage model = new ModelPage(driver);
        Assert.assertTrue("page navigated to correct model", popularModel.contains(model.GetModelNameText()));

        model.ClickBuggyRatingLink();
        Assert.assertEquals("Expected to be back on the main page", "https://buggy.justtestit.org/", driver.getCurrentUrl());
    }

    @Then("I navigate to Overall Rating then confirm overall page and back to main page successfully")
    public void iNavigateToOverallRatingThenConfirmOverallPageAndBackToMainPageSuccessfully() {

        HomePage home = new HomePage(driver);

        home.ClickOverallLink();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("Expected to be on the overall page","https://buggy.justtestit.org/", driver.getCurrentUrl());

        home.ClickBuggyRatingLink();
        Assert.assertEquals("Expected to be back on the main page","https://buggy.justtestit.org/", driver.getCurrentUrl());
    }
}
