Feature: Dashboard Inventory

  @Dashboard @Export
  Scenario: Memverifikasi fitur export data barang
    Given Superadmin sudah login dan berada di halaman Dashboard
    When Superadmin menekan tombol Export
    And Superadmin pilih menu PDF
    Then File laporan PDF berhasil terunduh ke laptop