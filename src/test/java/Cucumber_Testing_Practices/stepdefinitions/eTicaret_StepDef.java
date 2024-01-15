package Cucumber_Testing_Practices.stepdefinitions;

import Cucumber_Testing_Practices.kisayollar.kisayollarxD;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class eTicaret_StepDef
{
    kisayollarxD xD;
    WebDriver driver;
    String handle1;
    String handle2;
    String searchtext="Bilgisayar";

    @Given("user go to the trendyol")
    public  void userGoToTheTrendyol()
    {
        xD=new kisayollarxD();
        driver=new ChromeDriver();
        System.setProperty("webdriver.chrome.driver",xD.driverPath);

        driver.get("https://www.trendyol.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        handle1= driver.getWindowHandle();
    }

    @When("user search the bilgisayar")
    public void userSearchTheBilgisayar()
    {
        driver.findElement(By.id("Rating-Review")).click();                 // popup close button.
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();   // cookies accept button.

        driver.findElement(By.xpath("//div/*[@placeholder=\"Aradığınız ürün, kategori veya markayı yazınız\"]"))
                .sendKeys(searchtext, Keys.ENTER);                          // search the "Bilgisayar"
    }

    @When("user click the first item")
    public void userClickTheFirstItem()
    {
        driver.findElement(By.xpath("(//*[@class=\"image-overlay-body\"])[1]")).click();

        Set<String>handledegerleri=driver.getWindowHandles();
        for (String each:handledegerleri)                           // ileride birden fazla sekme ile çalışılacak olursa
        {                                                           // bu kod bloğu ile çalışılabilir. Sadee 2 sekme için
            if(!each.equals(handle2)) handle2=each;                 // gereksizdir.
        }
    }

    @Then("user verify the item name contains the bilgisayar")
    public void userVerifyTheItemNameContainsTheBilgisayar() {
        System.out.println("handle1 = " + handle1);
        System.out.println("handle2 = " + handle2);

        driver.switchTo().window(handle2);

        WebElement a=driver.findElement(By.xpath("//h1[@class=\"pr-new-br\"]"));

        String alert=a.getText().contains(searchtext)?"Test is PASSED":"Test is FAILED";
        System.out.println(alert);

        driver.close();

        driver.switchTo().window(handle1);
        driver.close();
    }
}
