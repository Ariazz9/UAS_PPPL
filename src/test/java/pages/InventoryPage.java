package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
    WebDriver driver;

    // Locator Dinamis berdasarkan Label Form
    private By btnTambahBarang = By.xpath("//button[normalize-space()='Tambah']"); // Asumsi teks tombolnya ini
    private By btnSimpan = By.xpath("//button[@type='submit'][normalize-space()='Tambah']"); // Asumsi teks tombolnya ini

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
    }
    public void klikMenuInventoryDiSidebar() {
        driver.findElement(menuInventorySidebar).click();
    }

    public void klikTambahBarangBaru() {
        driver.findElement(btnTambahBarang).click();
    }
    public void isiFormLengkap(String nama, String kategori, String material, String supplier, String jumlah, String unit, String harga) throws InterruptedException {
        driver.findElement(inputNama).sendKeys(nama);
        new Select(driver.findElement(selectKategori)).selectByVisibleText(kategori);
        new Select(driver.findElement(selectMaterial)).selectByVisibleText(material);
        new Select(driver.findElement(selectSupplier)).selectByVisibleText(supplier);

        WebElement fieldJumlah = driver.findElement(inputJumlah);
        fieldJumlah.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        fieldJumlah.sendKeys(jumlah);

        WebElement fieldUnit = driver.findElement(inputUnit);
        fieldUnit.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        fieldUnit.sendKeys(unit);

        WebElement fieldHarga = driver.findElement(inputHarga);
        fieldHarga.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        fieldHarga.sendKeys(harga);

        Thread.sleep(1500);
    }

    public void klikSimpan() {
        driver.findElement(btnSimpan).click();
    }
}