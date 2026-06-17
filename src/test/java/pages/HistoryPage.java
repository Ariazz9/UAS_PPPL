package pages;

import org.openqa.selenium.*;

public class HistoryPage {
    WebDriver driver;
    private By inputCari = By.xpath("//input[@placeholder='Cari Barang']");
    private By tableRow = By.xpath("//tbody/tr");

    public HistoryPage(WebDriver driver) { this.driver = driver; }

    public void cariBarang(String nama) {
        WebElement el = driver.findElement(inputCari);
        el.clear();
        el.sendKeys(nama);
        el.sendKeys(Keys.ENTER);
    }

    public boolean verifikasiData(String nama) {
        return driver.getPageSource().contains(nama);
    }
}