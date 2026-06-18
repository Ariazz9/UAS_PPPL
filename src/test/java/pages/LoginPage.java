package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    private By inputEmail = By.xpath("//input[@placeholder='Enter Username or Email']");
    private By inputPassword = By.xpath("//input[@placeholder='1234']");
    private By btnContinue = By.xpath("//button[@id='kt_sign_in_submit']");

    // Locator yang disesuaikan persis dengan HTML web Delova-mu
    private By errorMsg = By.xpath("//div[text()='Login Gagal']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUsernameValue() {
        return driver.findElement(inputEmail).getAttribute("value");
    }

    public void inputUsername(String username) {
        driver.findElement(inputEmail).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.findElement(inputEmail).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(inputPassword).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.findElement(inputPassword).sendKeys(password);
    }

    public void klikLanjut() {
        WebElement tombolSubmit = driver.findElement(btnContinue);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", tombolSubmit);
    }

    public boolean isErrorMuncul() {
        try {
            return driver.findElement(errorMsg).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false; // Mengembalikan false jika tidak ada error di layar
        }
    }
}