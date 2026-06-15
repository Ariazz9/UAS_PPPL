Feature: User Registration
  Sebagai calon pengguna web inventory
  Saya ingin bisa mendaftar akun baru
  Agar saya bisa mengakses fitur dashboard dan master data

  @Positive
  Scenario: Registrasi berhasil dengan data yang valid
    Given User berada di halaman registrasi
    When User memasukkan nama "Ody Hartama"
    And User memasukkan email "ody@example.com"
    And User memasukkan password "Password123!"
    And User menekan tombol register
    Then User diarahkan ke halaman login
    And Sistem menampilkan pesan sukses "Registrasi berhasil"

  @Negative
  Scenario Outline: Registrasi gagal karena validasi input (BVA & EP)
    Given User berada di halaman registrasi
    When User memasukkan nama "<nama>"
    And User memasukkan email "<email>"
    And User memasukkan password "<password>"
    And User menekan tombol register
    Then Sistem menampilkan pesan error "<pesan_error>"

    Examples:
      | nama  | email           | password | pesan_error                  |
      |       | ody@example.com | Pass123! | Nama tidak boleh kosong      |
      | Ody   | ody.com         | Pass123! | Format email tidak valid     |
      | Ody   | ody@example.com | 1234567  | Password minimal 8 karakter  |