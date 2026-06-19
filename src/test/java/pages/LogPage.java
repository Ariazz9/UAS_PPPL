package pages;

import org.openqa.selenium.*;

public class LogPage {
    WebDriver driver;
    private By badgeAction = By.xpath("//span[contains(@class, 'badge-light-success')]");

    public LogPage(WebDriver driver) { this.driver = driver; }

    public String getAksiTerbaru() {
        return driver.findElement(badgeAction).getText();
    }
}