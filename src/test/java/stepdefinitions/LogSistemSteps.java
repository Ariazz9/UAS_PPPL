package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.LogPage;

public class LogSistemSteps {
    WebDriver driver;

    @Given("Super Admin sudah login dan berada di halaman Log Sistem")
    public void keLogSistem() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app-delova.vercel.app/auth/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("superadmin2");
        loginPage.inputPassword("password456");
        loginPage.klikLanjut();
        Thread.sleep(4000);

        driver.get("https://app-delova.vercel.app/apps/log-system"); // Pastikan URL ini benar
        Thread.sleep(2000);
    }

    @When("Super Admin melihat tabel Log Sistem")
    public void lihatLog() {
        // Cukup navigasi saja, aksi verifikasi ada di Then
    }

    @Then("Baris log terbaru harus menampilkan aksi {string} untuk tabel {string}")
    public void cekLog(String aksi, String tabel) {
        LogPage lp = new LogPage(driver);
        String aksiTerdeteksi = lp.getAksiTerbaru();

        // Asumsi: aksiTerdeteksi akan mengembalikan string seperti "create" atau "update"
        assert aksiTerdeteksi.equalsIgnoreCase(aksi) : "Log tidak mencatat aksi " + aksi;

        System.out.println("✅ [LOG VERIFIED] Aksi " + aksi + " tercatat di sistem.");
        driver.quit();
    }
}