Feature: Inventory (Ambil Barang)

  @Negative @Positive @E2E @EquivalencePartitioning
  Scenario Outline: Validasi berbagai aturan batas saat Super Admin mengambil barang
    Given Super Admin sudah login dan membuka form Ambil Barang "Kain Hasil Testing"
    When Super Admin mengisi form pengambilan dengan jumlah "<jumlah_ambil>" dan deskripsi "<deskripsi_ambil>"
    And Super Admin menekan tombol Potong Stok
    Then Sistem akan memproses dan memberikan respon status "<hasil_yang_diharapkan>"

    Examples:
      | jumlah_ambil | deskripsi_ambil                | hasil_yang_diharapkan |
      | 0            |                                | Ditolak               |
      | 101          |                                | Ditolak               |
      | 1            | Ambil 1 aja                    | Berhasil              |
      | 99           | Pengambilan untuk event        | Berhasil              |