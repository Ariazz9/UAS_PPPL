package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    WebDriver driver;
    WebDriverWait wait; // Tambahkan variabel WebDriverWait

    // Locator Dinamis berdasarkan Label Form
    private By btnTambahBarang = By.xpath("//button[normalize-space()='Tambah']");
    private By btnSimpan = By.xpath("//button[@type='submit'][normalize-space()='Tambah']");

    private By inputNama = By.xpath("//label[text()='Nama']/following-sibling::input");
    private By selectKategori = By.xpath("//label[text()='Kategori']/following-sibling::select");
    private By selectMaterial = By.xpath("//label[text()='Material']/following-sibling::select");
    private By selectSupplier = By.xpath("//label[text()='Supplier']/following-sibling::select");
    private By inputJumlah = By.xpath("//label[text()='Jumlah']/following-sibling::input");
    private By inputUnit = By.xpath("//label[text()='Unit']/following-sibling::input");
    private By inputHarga = By.xpath("//label[text()='Harga']/following-sibling::input");

    private By menuInventorySidebar = By.xpath("//img[@alt='Inventory']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void klikMenuInventoryDiSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(menuInventorySidebar)).click();
    }

    public void klikTambahBarangBaru() {
        wait.until(ExpectedConditions.elementToBeClickable(btnTambahBarang)).click();
    }

    public void isiFormLengkap(String nama, String kategori, String material, String supplier, String jumlah, String unit, String harga) {

        WebElement fieldNama = wait.until(ExpectedConditions.visibilityOfElementLocated(inputNama));
        fieldNama.sendKeys(nama);
        WebElement dropdownKategori = wait.until(ExpectedConditions.elementToBeClickable(selectKategori));
        new Select(dropdownKategori).selectByVisibleText(kategori);

        WebElement dropdownMaterial = wait.until(ExpectedConditions.elementToBeClickable(selectMaterial));
        new Select(dropdownMaterial).selectByVisibleText(material);

        WebElement dropdownSupplier = wait.until(ExpectedConditions.elementToBeClickable(selectSupplier));
        new Select(dropdownSupplier).selectByVisibleText(supplier);

        WebElement fieldJumlah = driver.findElement(inputJumlah);
        fieldJumlah.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        fieldJumlah.sendKeys(jumlah);

        WebElement fieldUnit = driver.findElement(inputUnit);
        fieldUnit.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        fieldUnit.sendKeys(unit);

        WebElement fieldHarga = driver.findElement(inputHarga);
        fieldHarga.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        fieldHarga.sendKeys(harga);

        // HAPUS Thread.sleep(1500)
    }

    public void klikSimpan() {
        // Tunggu tombol simpan bisa diklik sebelum mengeksekusinya
        wait.until(ExpectedConditions.elementToBeClickable(btnSimpan)).click();
    }
}