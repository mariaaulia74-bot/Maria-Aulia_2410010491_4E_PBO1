# Proyek Akhir Pemrograman Berbasis Objek 1

## Profil
* **Nama:** Maria Aulia
* **NPM:** 2410010491
* **Kelas:** 4E REG BJM
* **Mata Kuliah:** Pemrograman Berbasis Objek I

## Studi Kasus
Aplikasi Sistem Manajemen Perpustakaan Digital interaktif berbasis teks (Console) yang mendukung pengelolaan data pustaka (Buku Fisik dan E-Book), manajemen data anggota (Mahasiswa dan Dosen), pencatatan proses peminjaman, serta perhitungan pengembalian buku beserta sistem denda keterlambatan secara otomatis.

## Tabel Penilaian Mandiri

| No | Materi | Nilai Maksimal | Penilaian Mandiri | Keterangan / Nama Class & File |
|---|---|---|---|---|
| 1 | Class | 5 | 5 | Membuat class Main, Perpustakaan, Peminjaman, Denda, Anggota, Pustaka, BukuFisik, EBook |
| 2 | Object | 5 | 5 | Instansiasi objek `new Perpustakaan()`, `new BukuFisik()`, `new EBook()`, dan `new Anggota()` pada Main.java |
| 3 | Atribut | 5 | 5 | Atribut seperti id, nama, jenis pada Anggota.java; serta kode, judul, pengarang pada Pustaka.java |
| 4 | Constructor | 5 | 5 | Terdapat constructor pada class-class model (Anggota, BukuFisik, EBook) untuk inisialisasi data objek |
| 5 | Mutator | 5 | 5 | Method setter (misal: setStok, setLokasi) yang digunakan untuk memodifikasi data atribut |
| 6 | Accessor | 5 | 5 | Method getter (misal: `denda.getTotalDenda()`, `inputTanggal.isEmpty()`) untuk mengambil nilai atribut |
| 7 | Encapsulation | 5 | 5 | Semua atribut pada class model menggunakan modifier `private` dan diakses aman via Getter/Setter |
| 8 | Inheritance | 5 | 5 | Class `BukuFisik.java` dan `EBook.java` menggunakan keyword `extends` ke class induk `Pustaka.java` |
| 9 | Polymorphism | 10 | 10 | Penerapan polimorfisme saat objek `BukuFisik` dan `EBook` dimasukkan ke array induk via `tambahPustaka(Pustaka pustaka)` |
| 10 | Seleksi | 5 | 5 | Struktur `switch-case` untuk menu utama dan `if-else` untuk pengecekan jenis pustaka pada Main.java |
| 11 | Perulangan | 5 | 5 | Menggunakan perulangan `while (berjalan)` pada Main.java agar menu interaktif terus berulang |
| 12 | IO Sederhana | 10 | 10 | Menggunakan `Scanner sc = new Scanner(System.in)` dan `sc.nextLine()` untuk membaca input dari user |
| 13 | Array | 15 | 15 | Menggunakan Collection/ArrayList di dalam `Perpustakaan.java` untuk menampung list pustaka, anggota, dan denda |
| 14 | Error Handling | 15 | 15 | Menggunakan `try-catch` untuk `NumberFormatException` dan menangkap custom exception pada `perpustakaan.exception` |
| | **TOTAL** | **100** | **100** | |

## Penjelasan Singkat Penerapan Fitur PBO
1. **Class & Object:** Berhasil mengimplementasikan class struktural dan class model lalu melakukan instansiasi objeknya pada `Main.java`.
2. **Encapsulation:** Atribut dilindungi dengan akses `private` dan dimanipulasi dengan aman menggunakan `Getter` (Accessor) serta `Setter` (Mutator).
3. **Inheritance & Polymorphism:** Class `BukuFisik` dan `EBook` mewarisi sifat class induk `Pustaka`, di mana penambahannya ditangani secara polimorfik oleh satu method kolektif.
4. **Struktur Kontrol (Seleksi & Perulangan):** Menggunakan `switch-case` dan `if-else` sebagai pengatur logika aplikasi, dibungkus perulangan `while` agar menu terus interaktif.
5. **Koleksi & Validasi (Array & Error Handling):** Menyimpan data dinamis menggunakan Array/ArrayList di dalam sistem perpustakaan dan mengamankan input dari crash menggunakan struktur `try-catch`.

## Tautan Video Youtube
[https://youtu.be/z-ftP-kgdio]
