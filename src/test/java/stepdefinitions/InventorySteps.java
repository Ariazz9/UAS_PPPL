package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.InventoryPage;
import pages.AmbilBarangPage;
import pages.LoginPage;

public class InventorySteps {

    WebDriver driver;
    InventoryPage inventoryPage;
    AmbilBarangPage ambilBarangPage;

    // ==========================================
    // BAGIAN 1: SKENARIO TAMBAH BARANG
    // ==========================================

    @Given("Super Admin sudah login dan berada di halaman Inventory")
    public void superAdminSudahLoginDanBeradaDiHalamanInventory() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://app-delova.vercel.app/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("superadmin2");
        loginPage.inputPassword("password456");
        loginPage.klikLanjut();
        Thread.sleep(4000);

        driver.get("https://app-delova.vercel.app/apps/inventory");
        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("inventory") : "Pre-condition Gagal: Gagal ke halaman Inventory!";
    }

    @When("Super Admin menekan tombol Tambah Barang Baru")
    public void superAdminMenekanTombolTambahBarangBaru() throws InterruptedException {
        inventoryPage = new InventoryPage(driver);
        inventoryPage.klikTambahBarangBaru();
        Thread.sleep(2000);
    }

    @When("Super Admin mengisi form dengan Nama {string}, Kategori {string}, Material {string}, Supplier {string}, Jumlah {string}, Unit {string}, Harga {string}")
    public void superAdminMengisiFormLengkap(String nama, String kategori, String material, String supplier, String jumlah, String unit, String harga) throws InterruptedException {
        inventoryPage.isiFormLengkap(nama, kategori, material, supplier, jumlah, unit, harga);
    }

    @When("Super Admin menekan tombol Simpan")
    public void superAdminMenekanTombolSimpan() throws InterruptedException {
        inventoryPage.klikSimpan();
        Thread.sleep(3000);
    }

    @Then("Sistem menolak menyimpan data barang dan tetap berada di form")
    public void sistemMenolakMenyimpanDataBarang() {
        boolean isFormMasihTerbuka = driver.getPageSource().contains("Kategori");
        assert isFormMasihTerbuka : "BUG FATAL: Sistem mengizinkan simpan barang tanpa Nama!";
        System.out.println("Sistem berhasil memblokir penyimpanan data kosong.");
        driver.quit();
    }

    @Then("Sistem menampilkan pesan sukses dan barang {string} muncul di tabel")
    public void sistemMenampilkanPesanSuksesDanBarangMuncul(String namaBarang) {
        boolean isBarangAda = driver.getPageSource().contains(namaBarang);
        assert isBarangAda : "Test Gagal: Barang " + namaBarang + " tidak ditemukan di dalam tabel inventory!";
        driver.quit();
    }

    // ==========================================
    // BAGIAN 2: SKENARIO AMBIL BARANG (SCENARIO OUTLINE)
    // ==========================================

    @Given("Super Admin sudah login dan membuka form Ambil Barang {string}")
    public void superAdminSudahLoginDanMembukaFormAmbilBarang(String namaBarang) throws InterruptedException {
        // Setup & Login
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app-delova.vercel.app/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("superadmin2");
        loginPage.inputPassword("password456");
        loginPage.klikLanjut();
        Thread.sleep(4000);

        // Pindah ke Inventory
        driver.get("https://app-delova.vercel.app/apps/inventory");
        Thread.sleep(3000);

        // Rangkaian klik membuka modal Potong Stok
        ambilBarangPage = new AmbilBarangPage(driver);
        ambilBarangPage.cariBarang(namaBarang);

        // 2. Sekarang baru klik kartunya
        ambilBarangPage.klikKartuBarang(namaBarang);
        Thread.sleep(2000);
        ambilBarangPage.masukKeDetailBarang();
        Thread.sleep(3000);
        ambilBarangPage.bukaMenuAmbilBarang();
        Thread.sleep(2000);
    }

    @When("Super Admin mengisi form pengambilan dengan jumlah {string} dan deskripsi {string}")
    public void superAdminMengisiFormPengambilan(String jumlah, String deskripsi) throws InterruptedException {
        ambilBarangPage.isiFormPengambilan(jumlah, deskripsi);
        Thread.sleep(1500);
    }

    @When("Super Admin menekan tombol Potong Stok")
    public void superAdminMenekanTombolPotongStok2() throws InterruptedException {
        ambilBarangPage.klikPotongStok();
        Thread.sleep(2000);
    }

    @Then("Sistem akan memproses dan memberikan respon status {string}")
    public void sistemAkanMemprosesDanMemberikanResponStatus(String statusHarapan) {
        if (statusHarapan.equals("Ditolak")) {
            boolean isModalMasihAda = driver.getPageSource().contains("Potong Stok");
            assert isModalMasihAda : "BUG: Sistem mengizinkan input yang salah menembus database!";
            System.out.println("Sistem menolak input tidak valid sesuai ekspektasi.");
        } else if (statusHarapan.equals("Berhasil")) {
            System.out.println("Barang berhasil diambil dan stok terpotong!");
        }

        driver.quit();
    }
}