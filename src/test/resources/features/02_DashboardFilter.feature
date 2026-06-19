Feature: Filter Dashboard (Items Activity)

  @Positive @E2E
  Scenario Outline: Super Admin memfilter tabel Items Activity berdasarkan Kategori
    Given Super Admin sudah login dan berada di halaman Dashboard
    When Super Admin menekan tombol filter Kategori pada bagian Items Activity
    And Super Admin memilih kategori "<kategori_dipilih>"
    Then Tabel Items Activity memfilter dan menampilkan barang "<barang_diharapkan>"

    Examples:
      | kategori_dipilih | barang_diharapkan  |
      | Kain             | Kain Rajut Solo |
      | Benang           | Benang Linen           |