package Cucumber_Testing_Practices.stepdefinitions;

import Cucumber_Testing_Practices.kisayollar.kisayollarxD;
import Cucumber_Testing_Practices.kisayollar.readJsonFile;
import Cucumber_Testing_Practices.kisayollar.register;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class linkedin_StepDef {
    kisayollarxD xD;
    readJsonFile json;
    Cucumber_Testing_Practices.kisayollar.register register;
    WebDriver driver;
    String currentURL;
    String profiletitle;
    @Given("go_to_the_linkedin")
    public void go_to_the_linkedin() {
        driver=new ChromeDriver();
        xD=new kisayollarxD();
        register=new register();
        System.setProperty("webdriver.chrome.driver","C:\\project\\ChromeDriver\\chromedriver.exee");   // chromedriver.exe nin pathini buraya giriniz.
        driver.get("https://www.linkedin.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    // Background işlemi burada yapıldı sadece linkedin sayfasına gidilmesi
    // ortak olduğu için tek bir adımda oldu lakin uzatılabilir.


    @When("click the sign in button")
    public void clickTheSignInButton()
    {
        driver.findElement(By.xpath("//a[@data-test-id=\"sign-in-join-cta\"]")).click();
    }

    @When("enter the e-mail and password")
    public void enterTheEMailAndPassword()
    {
        driver.findElement(By.id("email-address")).sendKeys(register.mail);
        driver.findElement(By.id("password")).sendKeys(register.pass);
        driver.findElement(By.id("join-form-submit")).click();
        driver.findElement(By.id("first-name")).sendKeys(register.name);
        driver.findElement(By.id("last-name")).sendKeys(register.lastName);
        driver.findElement(By.id("join-form-submit")).click();
    }

    @Then("verify the account was create succesfuly")
    public void verifyTheAccountWasCreateSuccesfuly() throws InterruptedException
    {
        WebElement dogrulama=new WebDriverWait(driver,Duration.ofSeconds(15))                       // doğrulama popupı açılıncaya kadar
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("challenge-title")));    // 15 saniye boyunca bekleme.

        if(dogrulama.getText().contains("Güvenlik") || dogrulama.getText().contains("doğrulama"))
        {
            System.out.println("Test Passed");

            Thread.sleep(2000);
            driver.close();                           // Test başarılı olduktan sonra doğrulamayı
            driver = null;                           // gözle görebilmek için hard wait.
        }
        else
        {
            System.out.println("Test not run to verify");
            driver.close();
            driver = null;
        }
    }                                                // First Scenario burada bitiyor ve driver ı kapatıyoruz.



    @When("enter the mail and password")
    public void enterTheMailAndPassword() throws IOException, ParseException {
        json=new readJsonFile();

        for (int i=0;i<json.readJson().length;i++)
        {
            String[] mailandpass = json.readJson()[i].split(",");
            driver.findElement(By.id("session_key")).sendKeys(mailandpass[0]);
            driver.findElement(By.id("session_password")).sendKeys(mailandpass[1]);
            driver.findElement(By.xpath("//button[@data-id=\"sign-in-form__submit-btn\"]")).click();
            if(driver.getTitle().contains("Feed"))
            {
                break;
            }
        }

    }

    @When("search the @user")
    public void searchTheUser()
    {
        driver.findElement(By.xpath("//*[@placeholder=\"Search\"]")).sendKeys("Barış Aşkın", Keys.ENTER);

    }

    @When("click the first person")
    public void clickTheFirstPerson()
    {
        driver.findElement(By.xpath("(//*[@class=\"app-aware-link \"])[2]")).click();       //önerilen ilk çıkan profile tıklıyoruz
        currentURL=driver.getCurrentUrl();
    }

    @Then("verify")
    public void verify()
    {

        profiletitle=driver.findElement(By.xpath("//*[@class=\"text-body-medium break-words\"]")).getText();
        String alert=(currentURL.contains("baris")||currentURL.contains("askin")&& profiletitle.contains("Orion")||profiletitle.contains("Test"))?"Test is PASSED":"Test is FAILED";
        System.out.println(alert);
        driver.close();
    }
}
