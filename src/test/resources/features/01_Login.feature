Feature: Autentikasi Super Admin
  Sebagai Super Admin Delova
  Saya ingin login ke dalam sistem
  Agar saya dapat mengakses dashboard dan mengelola inventaris

  @Negative @EquivalencePartitioning
  Scenario Outline: Login gagal dengan format email/username dan password tidak valid atau salah
    Given Super Admin berada di halaman Login
    When Super Admin memasukkan email "<email_input>"
    And Super Admin memasukkan password "<password_input>"
    And Super Admin menekan tombol Continue
    Then Sistem memunculkan pesan error dan tetap berada di halaman Login

    Examples:
      | email_input       | password_input |
      | admin1            | password6969   |
      | miguel@gmail.com  | password123    |
      | PriaSolo@gmail    | password6969   |

  @Positive @E2E
  Scenario: Login berhasil dengan kredensial yang valid
    Given Super Admin berada di halaman Login
    When Super Admin memasukkan email atau username "superadmin2" yang valid
    And Super Admin memasukkan password yang valid
    And Super Admin menekan tombol Continue
    Then Super Admin berhasil masuk dan diarahkan ke halaman Dashboard Utama