package BuggyCarsRating.Model.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ModelPage extends BasePage<ModelPage>
{

    public ModelPage(WebDriver driver)
    {
        super(driver);
    }

    public void SetCommentField(String comment) { driver.findElement(By.id("comment")).sendKeys(comment); }

    public String GetVoteNumber() { return driver.findElement(By.cssSelector("div:nth-child(1) > h4 > strong")).getText(); }

    public void ClickVoteButton() { driver.findElement(By.xpath("//button[@class='btn btn-success'][text()='Vote!']")).click(); }

    public String GetVoteMessageDoneText() { return driver.findElement(By.xpath("//p[@class='card-text']")).getText(); }

    public String GetModelNameText() { return driver.findElement(By.cssSelector("div:nth-child(2) > h3")).getText(); }
}
