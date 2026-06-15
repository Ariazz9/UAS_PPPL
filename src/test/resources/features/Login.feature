Feature: User Login
  Sebagai pengguna web inventory
  Saya ingin bisa masuk ke dalam sistem
  Agar saya bisa mengakses dashboard dan mengelola data inventory

  @Positive
  Scenario: Login berhasil dengan kredensial yang valid
    Given User berada di halaman login
    When User memasukkan email valid "admin@inventory.com"
    And User memasukkan password valid "Admin123!"
    And User menekan tombol login
    Then User diarahkan ke halaman dashboard utama
    And Sistem menampilkan pesan sukses "Selamat datang"

  @Negative
  Scenario Outline: Login gagal karena input tidak valid atau salah kredensial
    Given User berada di halaman login
    When User memasukkan email "<email>"
    And User memasukkan password "<password>"
    And User menekan tombol login
    Then Sistem menampilkan pesan error "<pesan_error>"

    Examples:
      | email               | password  | pesan_error                  |
      |                     | Admin123! | Email tidak boleh kosong     |
      | admin.com           | Admin123! | Format email tidak valid     |
      | admin@inventory.com | salah123  | Email atau password salah    |
      | admin@inventory.com |           | Password tidak boleh kosong  |