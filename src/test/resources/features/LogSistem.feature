Feature: Log Sistem (Activity Log)

  @Positive @E2E
  Scenario: Memverifikasi log aksi "create" oleh Super Admin
    Given Super Admin sudah login dan berada di halaman Log Sistem
    When Super Admin melihat tabel Log Sistem
    Then Baris log terbaru harus menampilkan aksi "update" untuk tabel "items"