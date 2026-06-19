package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.HistoryPage;

public class HistorySteps {
    WebDriver driver;

    @Given("Super Admin sudah login dan berada di halaman Histori Pengambilan")
    public void keHistori() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app-delova.vercel.app/auth/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputUsername("superadmin2");
        loginPage.inputPassword("password456");
        loginPage.klikLanjut();
        Thread.sleep(4000);

        driver.get("https://app-delova.vercel.app/apps/history");
        Thread.sleep(2000);
    }

    @When("Super Admin mencari barang {string} pada tabel histori")
    public void cariHistori(String nama) {
        HistoryPage hp = new HistoryPage(driver);
        hp.cariBarang(nama);
    }

    @Then("Sistem menampilkan baris data dengan nama barang {string}")
    public void cekHistori(String nama) {
        HistoryPage hp = new HistoryPage(driver);
        assert hp.verifikasiData(nama) : "Data barang tidak ditemukan di tabel histori!";
        System.out.println("Aksi " + nama + " tercatat di sistem.");
        driver.quit();
    }
}