package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmbilBarangPage {
    WebDriver driver;

    private By btnDetailBarang = By.xpath("//button[text()='Detail Barang']");
    private By btnAksi = By.xpath("//button[contains(normalize-space(), 'Aksi')]");
    private By menuAmbilBarang = By.xpath("//button[contains(normalize-space(), 'Ambil Barang')]");

    // Locator Form Potong Stok
    private By inputJumlah = By.xpath("//input[@type='number']");
    private By inputDeskripsi = By.xpath("//textarea");
    private By btnPotongStok = By.xpath("//button[contains(normalize-space(), 'Potong Stok')]");
    private By inputCari = By.xpath("//input[contains(@placeholder, 'Cari')]");

    public void cariBarang(String namaBarang) {
        WebElement fieldCari = driver.findElement(inputCari);
        fieldCari.clear();
        fieldCari.sendKeys(namaBarang);
        fieldCari.sendKeys(Keys.ENTER); // Tekan enter untuk memfilter tabel

        // Beri jeda agar tabel memfilter data
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    public AmbilBarangPage(WebDriver driver) {
        this.driver = driver;
    }

    public void klikKartuBarang(String namaBarang) {
        By kartuBarang = By.xpath("//h5[text()='" + namaBarang + "']");
        driver.findElement(kartuBarang).click();
    }

    public void masukKeDetailBarang() {
        driver.findElement(btnDetailBarang).click();
    }

    public void bukaMenuAmbilBarang() throws InterruptedException {
        driver.findElement(btnAksi).click();
        Thread.sleep(1000);
        driver.findElement(menuAmbilBarang).click();
    }

    public void isiFormPengambilan(String jumlah, String deskripsi) {
        WebElement fieldJumlah = driver.findElement(inputJumlah);
        fieldJumlah.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        fieldJumlah.sendKeys(jumlah);

        WebElement fieldDeskripsi = driver.findElement(inputDeskripsi);
        fieldDeskripsi.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        fieldDeskripsi.sendKeys(deskripsi);
    }

    public void klikPotongStok() {
        driver.findElement(btnPotongStok).click();
    }
}