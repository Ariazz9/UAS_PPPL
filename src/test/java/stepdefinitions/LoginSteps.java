package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    String defaultUsername;

    @Given("Super Admin berada di halaman Login")
    public void superAdminBeradaDiHalamanLogin() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app-delova.vercel.app/auth/login");

        loginPage = new LoginPage(driver);
        defaultUsername = loginPage.getUsernameValue();
    }

    @When("Super Admin memasukkan email atau username {string} yang valid")
    public void superAdminMemasukkanEmailatauUsernameYangValid(String email) throws InterruptedException {
        loginPage.inputUsername(email);
        Thread.sleep(1500);
    }

    @When("Super Admin memasukkan password yang valid")
    public void superAdminMemasukkanPasswordYangValid() throws InterruptedException{
        loginPage.inputPassword("password456");
        Thread.sleep(1500);
    }

    @When("Super Admin memasukkan email {string}")
    public void superAdminMemasukkanEmail(String email) throws InterruptedException {
        loginPage.inputUsername(email);
        Thread.sleep(1500);
    }

    @When("Super Admin memasukkan password {string}")
    public void superAdminMemasukkanPassword(String password) throws InterruptedException{
        loginPage.inputPassword(password);
        Thread.sleep(1500);
    }

    @When("Super Admin menekan tombol Continue")
    public void superAdminMenekanTombolContinue() throws InterruptedException {
        loginPage.klikLanjut();
        Thread.sleep(2500);
    }

    @Then("Super Admin berhasil masuk dan diarahkan ke halaman Dashboard Utama")
    public void superAdminBerhasilMasuk() {
        assert driver.getCurrentUrl().contains("https://app-delova.vercel.app/dashboard") : "Gagal masuk ke dashboard!";
        System.out.println("Login berhasil!");
        driver.quit();
    }

    @Then("Sistem memunculkan pesan error dan tetap berada di halaman Login")
    public void sistemMenolakAksesDanTetapDiHalamanLogin() throws InterruptedException {
        Thread.sleep(1500);

        // 1. Validasi Bug Fix: Pastikan kotak merah "Login Gagal" muncul
        boolean isErrorTerlihat = loginPage.isErrorMuncul();
        assert isErrorTerlihat : "BUG REGRESI: Kredensial salah, tapi notifikasi error tidak muncul!";

        // 2. Validasi URL: Pastikan tidak tembus ke dashboard
        String currentUrl = driver.getCurrentUrl();
        assert !currentUrl.contains("dashboard") : "Test Gagal: Login invalid malah tembus ke dashboard!";
        assert currentUrl.contains("login") : "Test Gagal: Halaman ter-redirect ke tempat yang salah!";

        System.out.println("Sistem berhasil menolak login dan memunculkan pesan error.");
        driver.quit();
    }
}