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
        driver.quit();
    }

    @Then("Sistem menolak akses dan tetap berada di halaman Login")
    public void sistemMenolakAksesDanTetapDiHalamanLogin() throws InterruptedException {
        Thread.sleep(1500);
        String currentUrl = driver.getCurrentUrl();
        //Pastikan URL tidak tembus ke dashboard
        assert !currentUrl.contains("dashboard") : "Test Gagal: Login invalid malah tembus ke dashboard!";
        //Pastikan URL tetap tertahan di halaman login
        assert currentUrl.contains("login") : "Test Gagal: Halaman ter-redirect ke tempat yang salah!";

        driver.quit();
    }
}