# Proyek Akhir Pemrograman Berbasis Objek 1

Proyek ini adalah aplikasi Sistem Manajemen Perpustakaan Digital menggunakan Java sebagai tugas akhir dari mata kuliah pemrograman berbasis objek 1.

## Deskripsi

Aplikasi ini menerima input berupa data pustaka (buku fisik dan e-book), data anggota, dan transaksi peminjaman, lalu memberikan output berupa informasi status peminjaman, sisa stok buku, dan perhitungan denda keterlambatan.

Aplikasi ini mengimplementasikan beberapa konsep penting dalam pemrograman berorientasi objek (OOP) seperti Class, Object, Atribut, Method Constructor, Method Mutator, Method Accessor, Encapsulation, Inheritance, Polymorphism, Seleksi, Perulangan, IO Sederhana, Array, dan Error Handling.

## Penjelasan Kode

Berikut adalah bagian kode yang relevan dengan konsep OOP yang dijelaskan:

1. **Class** adalah template atau blueprint dari object. Pada kode ini, `Pustaka`, `BukuFisik`, dan `EBook` adalah contoh dari class.

```bash
public abstract class Pustaka {
    ...
}

public class BukuFisik extends Pustaka {
    ...
}
```

2. **Object** adalah instance dari class. Pada kode ini, `new BukuFisik("BK001", "Belajar Java Dasar", ...)` adalah contoh pembuatan object.

```bash
perpustakaan.tambahPustaka(new BukuFisik("BK001", "Belajar Java Dasar", "Andi Wijaya", 2020, 3, "Rak A1"));
```

3. **Atribut** adalah variabel yang ada dalam class. Pada kode ini, `judul` dan `pengarang` adalah contoh atribut.

```bash
private String judul;
private String pengarang;
```

4. **Constructor** adalah method yang pertama kali dijalankan pada saat pembuatan object. Pada kode ini, constructor ada di dalam class `Pustaka` dan `BukuFisik`.

```bash
public Pustaka(String kodePustaka, String judul, String pengarang, int tahunTerbit) {
    this.kodePustaka = kodePustaka;
    this.judul = judul;
    this.pengarang = pengarang;
    this.tahunTerbit = tahunTerbit;
}

public BukuFisik(String kodePustaka, String judul, String pengarang, int tahunTerbit, int stok, String lokasiRak) {
    super(kodePustaka, judul, pengarang, tahunTerbit);
    this.stok = stok;
    this.lokasiRak = lokasiRak;
}
```

5. **Mutator** atau setter digunakan untuk mengubah nilai dari suatu atribut. Pada kode ini, `setJudul` dan `setPengarang` adalah contoh method mutator.

```bash
public void setJudul(String judul) {
    this.judul = judul;
}

public void setPengarang(String pengarang) {
    this.pengarang = pengarang;
}
```

6. **Accessor** atau getter digunakan untuk mengambil nilai dari suatu atribut. Pada kode ini, `getJudul`, `getPengarang`, `getStok`, dan `getLinkUnduh` adalah contoh method accessor.

```bash
public String getJudul() {
    return judul;
}

public String getPengarang() {
    return pengarang;
}
```

7. **Encapsulation** adalah konsep menyembunyikan data dengan membuat atribut menjadi private dan hanya bisa diakses melalui method. Pada kode ini, atribut `judul` dan `stok` dienkapsulasi dan hanya bisa diakses melalui method getter dan setter.

```bash
private String judul;
private int stok;
```

8. **Inheritance** adalah konsep di mana sebuah class bisa mewarisi property dan method dari class lain. Pada kode ini, `BukuFisik` dan `EBook` mewarisi `Pustaka` dengan sintaks `extends`.

```bash
public class BukuFisik extends Pustaka {
    ...
}

public class EBook extends Pustaka {
    ...
}
```

9. **Polymorphism** adalah konsep di mana sebuah nama method yang sama dapat berperilaku berbeda tergantung pada tipe objek yang mereka manipulasi. Pada kode ini, method `aksesPustaka()` di `Pustaka` bersifat abstrak, lalu di-override berbeda oleh `BukuFisik` dan `EBook`.

```bash
// di BukuFisik
@Override
public String aksesPustaka() {
    return "Buku fisik dapat dipinjam langsung di lokasi rak: " + lokasiRak;
}

// di EBook
@Override
public String aksesPustaka() {
    return "E-Book dapat diakses dengan mengunduh dari: " + linkUnduh;
}
```

10. **Seleksi** adalah statement kontrol yang digunakan untuk membuat keputusan berdasarkan kondisi. Pada kode ini, digunakan seleksi `if else` dalam constructor `Anggota` dan seleksi `switch` dalam menu utama `Main.java`.

```bash
public Anggota(String idAnggota, String nama, String jenisAnggota) {
    ...
    if (jenisAnggota.equalsIgnoreCase("Dosen")) {
        this.maksimalPinjam = 5;
    } else {
        this.maksimalPinjam = 3;
    }
}

switch (pilihan) {
    case 1:
        perpustakaan.tampilkanSemuaPustaka();
        break;
    case 0:
        berjalan = false;
        break;
}
```

11. **Perulangan** adalah statement kontrol yang digunakan untuk menjalankan blok kode berulang kali. Pada kode ini, digunakan loop `while` untuk menu utama dan loop `for` untuk menelusuri data array.

```bash
while (berjalan) {
    tampilkanMenu();
    int pilihan = bacaPilihan();
    ...
}

for (int i = 0; i < jumlahPustaka; i++) {
    Pustaka p = daftarPustaka[i];
    System.out.println((i + 1) + ". " + p);
}
```

12. **Input Output Sederhana** digunakan untuk menerima input dari user dan menampilkan output ke user. Pada kode ini, digunakan class `Scanner` untuk menerima input dan method `System.out.println` untuk menampilkan output.

```bash
Scanner sc = new Scanner(System.in);
System.out.print("Kode pustaka: ");
String kode = sc.nextLine().trim();

System.out.println("Peminjaman berhasil dicatat: " + peminjaman);
```

13. **Array** adalah struktur data yang digunakan untuk menyimpan beberapa nilai dalam satu variabel. Pada kode ini, `Pustaka[] daftarPustaka = new Pustaka[100];` adalah contoh penggunaan array.

```bash
private Pustaka[] daftarPustaka;
private Anggota[] daftarAnggota;
private Peminjaman[] daftarPeminjaman;
private Denda[] daftarDenda;
```

14. **Error Handling** digunakan untuk menangani error yang mungkin terjadi saat runtime. Pada kode ini, digunakan `try catch` untuk menangani custom exception.

```bash
try {
    Peminjaman peminjaman = perpustakaan.pinjamPustaka(kode, id, LocalDate.now());
    System.out.println("Peminjaman berhasil dicatat: " + peminjaman);
} catch (DataTidakDitemukanException | StokTidakCukupException | BatasPinjamException e) {
    System.out.println("Gagal meminjam: " + e.getMessage());
}
```

## Usulan nilai

| No  | Materi         |  Nilai  |
| :-: | -------------- | :-----: |
|  1  | Class          |    5    |
|  2  | Object         |    5    |
|  3  | Atribut        |    5    |
|  4  | Constructor    |    5    |
|  5  | Mutator        |    5    |
|  6  | Accessor       |    5    |
|  7  | Encapsulation  |    5    |
|  8  | Inheritance    |    5    |
|  9  | Polymorphism   |   10    |
| 10  | Seleksi        |    5    |
| 11  | Perulangan     |    5    |
| 12  | IO Sederhana   |   10    |
| 13  | Array          |   15    |
| 14  | Error Handling |   15    |
|     | **TOTAL**      | **100** |

## Pembuat

Nama: Maria Aulia
NPM: 2410010491
