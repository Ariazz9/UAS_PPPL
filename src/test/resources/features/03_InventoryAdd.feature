Feature: Manajemen Inventory (Tambah barang)

  @Negative @EquivalencePartitioning
  Scenario: Super Admin gagal menambahkan barang karena Nama Barang kosong
    Given Super Admin sudah login dan berada di halaman Inventory
    When Super Admin menekan tombol Tambah Barang Baru
    And Super Admin mengisi form dengan Nama "", Kategori "Kain", Material "Katun", Supplier "Miguel", Jumlah "100", Unit "pcs", Harga "75000"
    And Super Admin menekan tombol Simpan
    Then Sistem menolak menyimpan data barang dan tetap berada di form

  @Positive @E2E
  Scenario: Super Admin berhasil menambahkan barang baru ke dalam sistem
    Given Super Admin sudah login dan berada di halaman Inventory
    When Super Admin menekan tombol Tambah Barang Baru
    And Super Admin mengisi form dengan Nama "Kain Tenun Enrique", Kategori "Kain", Material "Katun", Supplier "Miguel", Jumlah "100", Unit "pcs", Harga "75000"
    And Super Admin menekan tombol Simpan
    Then Sistem menampilkan pesan sukses dan barang "Kain Tenun Enrique" muncul di tabel

