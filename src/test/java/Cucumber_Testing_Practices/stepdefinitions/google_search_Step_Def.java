package Cucumber_Testing_Practices.stepdefinitions;

import Cucumber_Testing_Practices.kisayollar.kisayollarxD;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class google_search_Step_Def
{
    WebDriver driver;
    WebElement search;
    kisayollarxD xD;

    String searchtext="openai";
    @Given("user go to the google")
    public void userGoToTheGoogle()
    {
        xD= new kisayollarxD();
        driver=new ChromeDriver();
        System.setProperty("webdriver.chrome.driver",xD.driverPath);
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @When("user search the openai")
    public void userSearchTheOpenai()
    {
        search= driver.findElement(By.id("APjFqb"));
        search.sendKeys(searchtext, Keys.ENTER);
    }

    @When("user verify the search page")
    public void userVerifyTheSearchPage()
    {
        search=driver.findElement(By.id("APjFqb"));
        String alert=search.getText().equals(searchtext)?"Test is PASSED":"Test is FAILED";
        System.out.println(alert);
        System.out.println("expectedsearchbox.getText() = " + search.getText());
    }

    @Then("user close the page")
    public void userCloseThePage()
    {
        driver.quit();
    }
}
