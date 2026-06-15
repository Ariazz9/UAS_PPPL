package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.jupiter.api.Assertions;

public class stepDef {
    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("User is on the SauceDemo login page")
    public void userIsOnTheSauceDemoLoginPage() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        Assertions.assertTrue(loginPage.checkText().contains("Swag Labs"));
    }

    @When("User inputs a valid username {string}")
    public void userInputsAValidUsername(String username) {
        Assertions.assertTrue(loginPage.displayUserField());
        loginPage.setTrueUsername(username);
    }

    @When("User inputs a valid password {string}")
    public void userInputsAValidPassword(String password) {
        Assertions.assertTrue(loginPage.displayPasswordField());
        loginPage.setTruePassword(password);
    }

    @And("User clicks the login button")
    public void userClicksTheLoginButton() {
        Assertions.assertTrue(loginPage.displayButton());
        loginPage.submitButton();
    }

    @Then("User is redirected to the inventory page")
    public void userIsRedirectedToTheInventoryPage() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory.html"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}