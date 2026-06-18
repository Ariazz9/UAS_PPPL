Feature: Autentikasi Logout

  Sebagai Superadmin Delova
  Saya ingin bisa melakukan logout dari sistem
  Agar sesi akun saya aman dan tertutup setelah selesai menggunakan aplikasi

  @Logout @Positive @E2E
  Scenario: Memverifikasi fitur logout berjalan dengan baik
    Given Superadmin masih login dan berada di halaman Dashboard
    When Superadmin menekan menu Logout
    And Superadmin menekan tombol konfirmasi Logout
    Then Superadmin berhasil keluar dan diarahkan kembali ke halaman Login