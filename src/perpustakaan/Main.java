package perpustakaan;

import perpustakaan.model.*;
import perpustakaan.exception.*;
import perpustakaan.service.Perpustakaan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main adalah entry point dan menu interaktif untuk
 * Sistem Manajemen Perpustakaan Digital.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static Perpustakaan perpustakaan = new Perpustakaan();
    static DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        isiDataAwal();

        boolean berjalan = true;
        // Perulangan: menu utama akan terus muncul sampai user memilih keluar
        while (berjalan) {
            tampilkanMenu();
            int pilihan = bacaPilihan();

            // Seleksi: menentukan aksi berdasarkan pilihan menu
            switch (pilihan) {
                case 1:
                    perpustakaan.tampilkanSemuaPustaka();
                    break;
                case 2:
                    tambahPustakaBaru();
                    break;
                case 3:
                    tambahAnggotaBaru();
                    break;
                case 4:
                    perpustakaan.tampilkanSemuaAnggota();
                    break;
                case 5:
                    prosesPeminjaman();
                    break;
                case 6:
                    prosesPengembalian();
                    break;
                case 7:
                    perpustakaan.tampilkanRiwayatPeminjaman();
                    break;
                case 8:
                    perpustakaan.tampilkanDaftarDenda();
                    break;
                case 0:
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan Sistem Perpustakaan Digital!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
            System.out.println();
        }
        sc.close();
    }

    static void tampilkanMenu() {
        System.out.println("========================================");
        System.out.println(" SISTEM MANAJEMEN PERPUSTAKAAN DIGITAL");
        System.out.println("========================================");
        System.out.println("1. Tampilkan semua pustaka");
        System.out.println("2. Tambah pustaka baru");
        System.out.println("3. Tambah anggota baru");
        System.out.println("4. Tampilkan semua anggota");
        System.out.println("5. Pinjam pustaka");
        System.out.println("6. Kembalikan pustaka");
        System.out.println("7. Tampilkan riwayat peminjaman");
        System.out.println("8. Tampilkan daftar denda");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    // IO Sederhana + Error Handling: validasi input angka
    static int bacaPilihan() {
        try {
            int pilihan = Integer.parseInt(sc.nextLine().trim());
            return pilihan;
        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka!");
            return -1;
        }
    }

    static void tambahPustakaBaru() {
        System.out.println("Jenis pustaka: 1) Buku Fisik  2) E-Book");
        System.out.print("Pilih jenis: ");
        String jenis = sc.nextLine().trim();

        System.out.print("Kode pustaka: ");
        String kode = sc.nextLine().trim();
        System.out.print("Judul: ");
        String judul = sc.nextLine().trim();
        System.out.print("Pengarang: ");
        String pengarang = sc.nextLine().trim();

        try {
            System.out.print("Tahun terbit: ");
            int tahun = Integer.parseInt(sc.nextLine().trim());

            // Seleksi: jenis pustaka menentukan atribut tambahan yang diminta
            if (jenis.equals("1")) {
                System.out.print("Jumlah stok: ");
                int stok = Integer.parseInt(sc.nextLine().trim());
                System.out.print("Lokasi rak: ");
                String rak = sc.nextLine().trim();
                perpustakaan.tambahPustaka(new BukuFisik(kode, judul, pengarang, tahun, stok, rak));
                System.out.println("Buku fisik berhasil ditambahkan.");
            } else if (jenis.equals("2")) {
                System.out.print("Ukuran file (MB): ");
                double ukuran = Double.parseDouble(sc.nextLine().trim());
                System.out.print("Link unduh: ");
                String link = sc.nextLine().trim();
                perpustakaan.tambahPustaka(new EBook(kode, judul, pengarang, tahun, ukuran, link));
                System.out.println("E-Book berhasil ditambahkan.");
            } else {
                System.out.println("Jenis pustaka tidak dikenali, batal ditambahkan.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Terjadi kesalahan input angka: " + e.getMessage());
        }
    }

    static void tambahAnggotaBaru() {
        System.out.print("Id anggota: ");
        String id = sc.nextLine().trim();
        System.out.print("Nama: ");
        String nama = sc.nextLine().trim();
        System.out.print("Jenis anggota (Mahasiswa/Dosen): ");
        String jenis = sc.nextLine().trim();

        perpustakaan.tambahAnggota(new Anggota(id, nama, jenis));
        System.out.println("Anggota berhasil ditambahkan.");
    }

    static void prosesPeminjaman() {
        System.out.print("Kode pustaka: ");
        String kode = sc.nextLine().trim();
        System.out.print("Id anggota: ");
        String id = sc.nextLine().trim();

        try {
            Peminjaman peminjaman = perpustakaan.pinjamPustaka(kode, id, LocalDate.now());
            System.out.println("Peminjaman berhasil dicatat: " + peminjaman);
        } catch (DataTidakDitemukanException | StokTidakCukupException | BatasPinjamException e) {
            // Error Handling: menangkap beberapa jenis exception sekaligus
            System.out.println("Gagal meminjam: " + e.getMessage());
        }
    }

    static void prosesPengembalian() {
        System.out.print("Id peminjaman: ");
        String id = sc.nextLine().trim();
        System.out.print("Tanggal kembali (yyyy-MM-dd), kosongkan untuk hari ini: ");
        String inputTanggal = sc.nextLine().trim();

        try {
            LocalDate tanggalKembali = inputTanggal.isEmpty()
                    ? LocalDate.now()
                    : LocalDate.parse(inputTanggal, formatTanggal);

            Denda denda = perpustakaan.kembalikanPustaka(id, tanggalKembali);
            if (denda.getTotalDenda() > 0) {
                System.out.println("Pustaka dikembalikan dengan denda: " + denda);
            } else {
                System.out.println("Pustaka berhasil dikembalikan tanpa denda.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Format tanggal salah, gunakan format yyyy-MM-dd.");
        } catch (DataTidakDitemukanException e) {
            System.out.println("Gagal mengembalikan: " + e.getMessage());
        }
    }

    // Data awal contoh supaya program bisa langsung dicoba
    static void isiDataAwal() {
        perpustakaan.tambahPustaka(new BukuFisik("BK001", "Belajar Java Dasar", "Andi Wijaya", 2020, 3, "Rak A1"));
        perpustakaan.tambahPustaka(new BukuFisik("BK002", "Algoritma & Struktur Data", "Siti Rahma", 2019, 2, "Rak A2"));
        perpustakaan.tambahPustaka(new EBook("EB001", "Pemrograman Berorientasi Objek", "Budi Santoso", 2022, 4.5, "https://contoh.com/ebook/pbo"));
        perpustakaan.tambahPustaka(new EBook("EB002", "Pengantar Basis Data", "Rina Marlina", 2021, 3.2, "https://contoh.com/ebook/basisdata"));

        perpustakaan.tambahAnggota(new Anggota("A001", "Muhammad Fikri", "Mahasiswa"));
        perpustakaan.tambahAnggota(new Anggota("A002", "Dr. Hasanuddin", "Dosen"));
    }
}
