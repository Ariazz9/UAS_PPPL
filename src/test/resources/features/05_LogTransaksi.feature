Feature: Histori Pengambilan Barang

  @Positive @E2E
  Scenario: Admin mencari histori pengambilan barang
    Given Super Admin sudah login dan berada di halaman Histori Pengambilan
    When Super Admin mencari barang "Kain Rajut Solo" pada tabel histori
    Then Sistem menampilkan baris data dengan nama barang "Kain Rajut Solo"