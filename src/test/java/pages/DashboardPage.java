package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    private By btnFilterToggle = By.xpath("//button[following-sibling::div[contains(@class, 'bg-white') and contains(@class, 'shadow')]]");

    private By tableRow = By.xpath("//table//tbody//tr");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void bukaDropdownFilter() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnFilterToggle));
        btn.click();

        try { Thread.sleep(500); } catch (InterruptedException e) {}
    }

    public void pilihKategori(String namaKategori) {
        By opsiKategori = By.xpath("//div[contains(@class, 'bg-white') and contains(@class, 'shadow')]//div[normalize-space()='" + namaKategori + "']");

        WebElement opsi = wait.until(ExpectedConditions.elementToBeClickable(opsiKategori));
        opsi.click();

        // Beri jeda agar tabel memuat ulang data (karena React biasanya butuh waktu sepersekian detik untuk re-render state)
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }

    public boolean verifikasiBarangMunculDiTabel(String namaBarang) {
        // Cek apakah barang muncul di dalam tabel Items Activity
        By rowBarang = By.xpath("//table//tbody//tr//td[contains(normalize-space(), '" + namaBarang + "')]");
        return !driver.findElements(rowBarang).isEmpty();
    }
}