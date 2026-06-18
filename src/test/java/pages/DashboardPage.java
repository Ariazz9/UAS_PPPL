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

    // REVISI TOTAL: Target tombol langsung berdasarkan strukturnya, bukan elemen di sebelahnya
    private By btnFilterToggle = By.xpath("//div[@style='position: relative;']/button[contains(@class, 'btn-light') and contains(@class, 'gap-2')]");
    private By btnExport = By.xpath("//button[contains(normalize-space(), 'Export')]");
    private By menuPdf = By.xpath("//button[contains(normalize-space(), 'Export PDF')]");
    private By menuLogout = By.cssSelector("button[aria-label='Logout']");
    private By btnKonfirmasiLogout = By.xpath("//button[contains(@class, 'btn-danger') and text()='Logout']");

    // Locator tabel untuk verifikasi (tetap)
    private By tableRow = By.xpath("//table//tbody//tr");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void bukaDropdownFilter() {
        // Tunggu tombol bisa diklik
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnFilterToggle));
        btn.click();

        // Beri jeda sangat singkat agar React selesai merender DOM untuk menu dropdown
        try { Thread.sleep(500); } catch (InterruptedException e) {}
    }

    public void pilihKategori(String namaKategori) {
        // Karena setelah tombol diklik elemen dropdown-nya baru muncul di DOM, kita bisa mencarinya sekarang
        // Kita cari opsi berdasarkan teksnya
        By opsiKategori = By.xpath("//div[@style='position: relative;']//div[normalize-space()='" + namaKategori + "']");

        WebElement opsi = wait.until(ExpectedConditions.elementToBeClickable(opsiKategori));
        opsi.click();

        // Jeda agar tabel fetch data/re-render setelah filter aktif
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }

    public boolean verifikasiBarangMunculDiTabel(String namaBarang) {
        // Cek apakah barang muncul di dalam tabel Items Activity
        By rowBarang = By.xpath("//table//tbody//tr//td[contains(normalize-space(), '" + namaBarang + "')]");
        return !driver.findElements(rowBarang).isEmpty();
    }

    public void klikExport () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnExport));
        driver.findElement(btnExport).click();
    }

    public void klikPdf () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(menuPdf));
        driver.findElement(menuPdf).click();
    }

    public void klikLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(menuLogout));
        driver.findElement(menuLogout).click();
    }

    public void klikKonfirmasiLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(btnKonfirmasiLogout));
        driver.findElement(btnKonfirmasiLogout).click();
    }
}