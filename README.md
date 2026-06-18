# Pengujian End-to-End (E2E) Aplikasi Delova

Repository ini berisi otomatisasi pengujian (Automation Testing) untuk aplikasi manajemen inventaris **Delova**. Pengujian ini disusun untuk memenuhi tugas Responsi Praktikum Pengujian Perangkat Lunak.

## 1. Penjelasan Singkat SUT (System Under Test)
**SUT (System Under Test)** dalam proyek ini adalah **Aplikasi Web Delova** (`https://app-delova.vercel.app/`). Aplikasi ini merupakan sistem manajemen inventaris dan logistik dengan fitur utama yang diuji:
- Autentikasi Pengguna (Login Super Admin).
- Filter Dashboard (Aktivitas Item).
- Manajemen Data Barang (Menambah barang baru).
- Transaksi (Pemotongan stok/pengambilan barang).
- Pelacakan Aktivitas (Histori pengambilan dan Log Sistem).

## 2. Penjelasan Singkat Test Suite
Test Suite ini dibangun menggunakan pendekatan **Behavior-Driven Development (BDD)** dengan *framework* **Cucumber**, **Selenium WebDriver**, dan **JUnit 5**.

Test Suite terdiri dari **14 Test Case** yang mencakup pengujian fungsional *End-to-End* (E2E), *Positive Testing*, dan *Negative Testing* dengan mengimplementasikan teknik desain pengujian:
- **Equivalence Partitioning (EP):** Digunakan secara menyeluruh pada pengujian ini, meliputi pembagian partisi valid dan invalid untuk format input login, penanganan input wajib (mandatory field) pada form barang, serta pengelompokan nilai valid dan invalid (0, 101, 1, 99) secara terstruktur untuk menguji batas respons sistem pada alur pemotongan stok.

Skenario pengujian ditulis menggunakan format Gherkin (`Given, When, Then`) yang berfungsi ganda sebagai *Living Documentation*.

## 3. Pembagian Tugas dalam Kelompok
- **[Aurell Achmad Madina Hartama] :** Menyusun Skenario BDD (Test Case), mengembangkan *Step Definitions login, inventory*, menyusun laporan *Test Suite* & *README*, Melakukan setup arsitektur *Page Object Model (POM)*, konfigurasi *Dependencies* (Maven/POM.xml), *Test Runner*, setup auto report, dan menyusun dokumen *Bug Report*.
- **[Rizendy Affarin] :** Menyusun skenario BDD (Test Case), mengerjakan bagian testing pada laman 'Dashboard' berfokus pada fitur "Filter Aktivitas Barang", serta menyusun dokumen README.
- **[Mutia Umniati Z] :** Menyusun skenario BDD (Test Case), melakukan bug fixing pada negative test Login dan tabel Log, mengerjakan testing pada laman 'Dashboard' berfokus pada fitur Export PDF, melakukan testing fitur Logout, serta menyusun dokumen README.
- 
## 4. Struktur Repository
Berikut adalah struktur direktori dari repository ini:

```text
├── FolderDokumenTestSuite_dan_BugReport/                    # Berisi dokumen laporan pengujian
│   ├── Test_Suite.pdf         # Laporan Test Case (14 Skenario)
│   └── Bug_Report.pdf         # Laporan temuan Bug (Functional/Regression Bug pada Login)
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── pages/         # Page Object Model (POM) Locator & Actions
│   │   │   ├── runner/        # JUnit 5 Test Runners 
│   │   │   └── stepdefinitions/   # Implementasi Gherkin (Test Steps)
│   │   └── resources/
│   │       └── features/      # File Gherkin (.feature) Test Suite
├── target/                    # Folder hasil build dan Laporan HTML Cucumber (Automated Report)
├── pom.xml                    # Konfigurasi Maven (Dependencies)
└── README.md                  # Dokumentasi Repository
