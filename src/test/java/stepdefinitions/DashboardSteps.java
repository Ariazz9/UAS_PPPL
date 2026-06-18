package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.DashboardPage;

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

        System.out.println("✅ Filter berfungsi. Barang " + namaBarang + " muncul di tabel.");
        driver.quit();
    }
}