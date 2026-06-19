package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.DashboardPage;
import java.time.Duration;
import java.io.File;

public class DashboardSteps {
    WebDriver driver;
    DashboardPage dashboardPage;

    @Given("Super Admin sudah login dan berada di halaman Dashboard")
    public void superAdminSudahLoginDanBeradaDiHalamanDashboard() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app-delova.vercel.app/auth/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("superadmin2");
        loginPage.inputPassword("password456");
        loginPage.klikLanjut();

        // Tunggu login selesai (sebaiknya diganti explicit wait nanti)
        Thread.sleep(4000);

        dashboardPage = new DashboardPage(driver);
    }

    @When("Super Admin menekan tombol filter Kategori pada bagian Items Activity")
    public void klikFilterKategori() {
        dashboardPage.bukaDropdownFilter();
    }

    @When("Super Admin memilih kategori {string}")
    public void pilihKategoriFilter(String kategori) {
        dashboardPage.pilihKategori(kategori);
    }

    @Then("Tabel Items Activity memfilter dan menampilkan barang {string}")
    public void verifikasiTabelItemsActivity(String namaBarang) {
        boolean isBarangAda = dashboardPage.verifikasiBarangMunculDiTabel(namaBarang);
        assert isBarangAda : "Filter Gagal! Barang " + namaBarang + " tidak ditemukan di tabel setelah filter diterapkan.";

        System.out.println("Filter berfungsi. Barang " + namaBarang + " muncul di tabel.");
        driver.quit();
    }

    //Export

    @Given("Superadmin sudah login dan berada di halaman Dashboard")
    public void superAdminSudahLogin() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app-delova.vercel.app/auth/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("superadmin2");
        loginPage.inputPassword("password456");
        loginPage.klikLanjut();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        dashboardPage = new DashboardPage(driver);
    }

    @When("Superadmin menekan tombol Export")
    public void superadminKlikExport () {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.klikExport();
    }

    @And("Superadmin pilih menu PDF")
    public void pilihMenuPDF () {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.klikPdf();
    }

    @Then("File laporan PDF berhasil terunduh ke laptop")
    public void fileLaporanBerhasilTerunduh () throws InterruptedException {
        Thread.sleep(5000);
        String homepath = System.getProperty("user.home");
        String downloadpath = homepath + "\\Downloads";
        File folder = new File(downloadpath);
        File[] listOfFiles = folder.listFiles((dir, name) ->
                (name.startsWith("Ringkasan_Analisis") && name.endsWith(".pdf")));
        boolean isFileDownloaded = (listOfFiles != null && listOfFiles.length > 0);
        assert isFileDownloaded : "Test Gagal: File PDF export tidak ditemukan di folder Downloads laptop!";
        if (isFileDownloaded) {
            listOfFiles[0].delete();
        }
    }

    @After
    public void tutupBrowser () {
        if (driver != null) {
            driver.quit();
        }
    }

    //Logout
    @Given("Superadmin masih login dan berada di halaman Dashboard")
    public void superAdminMasihLogin() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app-delova.vercel.app/auth/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("superadmin2");
        loginPage.inputPassword("password456");
        loginPage.klikLanjut();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        dashboardPage = new DashboardPage(driver);
    }

    @When("Superadmin menekan menu Logout")
    public void superAdminMenekanMenuLogout() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.klikLogout();
    }

    @And("Superadmin menekan tombol konfirmasi Logout")
    public void superAdminMenekanTombolKonfirmasi () {
        dashboardPage.klikKonfirmasiLogout();
    }

    @Then("Superadmin berhasil keluar dan diarahkan kembali ke halaman Login")
    public void superAdminBerhasilKeluarDanKembaliKeHalamanLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("login"));
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("login") : "Test Gagal: Gagal logout, masih di halaman dashboard!";
    }
}