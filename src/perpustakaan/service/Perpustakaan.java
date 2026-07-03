package perpustakaan.service;

import perpustakaan.model.*;
import perpustakaan.exception.*;

import java.time.LocalDate;

/**
 * Class Perpustakaan adalah "otak" sistem: menyimpan seluruh data
 * memakai Array, melakukan pencarian dan transaksi memakai
 * Seleksi & Perulangan, serta melempar/menangani Exception saat
 * terjadi kondisi yang tidak valid.
 */
public class Perpustakaan {

    private static final int KAPASITAS = 100;

    // ===== Array sebagai penyimpanan data =====
    private Pustaka[] daftarPustaka;
    private int jumlahPustaka;

    private Anggota[] daftarAnggota;
    private int jumlahAnggota;

    private Peminjaman[] daftarPeminjaman;
    private int jumlahPeminjaman;

    private Denda[] daftarDenda;
    private int jumlahDenda;

    public Perpustakaan() {
        daftarPustaka = new Pustaka[KAPASITAS];
        daftarAnggota = new Anggota[KAPASITAS];
        daftarPeminjaman = new Peminjaman[KAPASITAS];
        daftarDenda = new Denda[KAPASITAS];
        jumlahPustaka = 0;
        jumlahAnggota = 0;
        jumlahPeminjaman = 0;
        jumlahDenda = 0;
    }

    // ===== Tambah data ke Array =====
    public void tambahPustaka(Pustaka p) {
        daftarPustaka[jumlahPustaka] = p;
        jumlahPustaka++;
    }

    public void tambahAnggota(Anggota a) {
        daftarAnggota[jumlahAnggota] = a;
        jumlahAnggota++;
    }

    // ===== Tampilkan data (Perulangan) =====
    public void tampilkanSemuaPustaka() {
        System.out.println("=== Daftar Pustaka ===");
        for (int i = 0; i < jumlahPustaka; i++) {
            Pustaka p = daftarPustaka[i];
            System.out.println((i + 1) + ". " + p);
            // Polymorphism: aksesPustaka() beda hasil tergantung objeknya
            System.out.println("   -> " + p.aksesPustaka());
        }
    }

    public void tampilkanSemuaAnggota() {
        System.out.println("=== Daftar Anggota ===");
        for (int i = 0; i < jumlahAnggota; i++) {
            System.out.println((i + 1) + ". " + daftarAnggota[i]);
        }
    }

    public void tampilkanRiwayatPeminjaman() {
        System.out.println("=== Riwayat Peminjaman ===");
        if (jumlahPeminjaman == 0) {
            System.out.println("Belum ada transaksi peminjaman.");
            return;
        }
        for (int i = 0; i < jumlahPeminjaman; i++) {
            System.out.println((i + 1) + ". " + daftarPeminjaman[i]);
        }
    }

    public void tampilkanDaftarDenda() {
        System.out.println("=== Daftar Denda ===");
        if (jumlahDenda == 0) {
            System.out.println("Belum ada denda tercatat.");
            return;
        }
        for (int i = 0; i < jumlahDenda; i++) {
            System.out.println((i + 1) + ". " + daftarDenda[i]);
        }
    }

    // ===== Pencarian (Perulangan + Seleksi) =====
    public Pustaka cariPustaka(String kode) throws DataTidakDitemukanException {
        for (int i = 0; i < jumlahPustaka; i++) {
            if (daftarPustaka[i].getKodePustaka().equalsIgnoreCase(kode)) {
                return daftarPustaka[i];
            }
        }
        throw new DataTidakDitemukanException("Pustaka dengan kode '" + kode + "' tidak ditemukan.");
    }

    public Anggota cariAnggota(String id) throws DataTidakDitemukanException {
        for (int i = 0; i < jumlahAnggota; i++) {
            if (daftarAnggota[i].getIdAnggota().equalsIgnoreCase(id)) {
                return daftarAnggota[i];
            }
        }
        throw new DataTidakDitemukanException("Anggota dengan id '" + id + "' tidak ditemukan.");
    }

    private int hitungPeminjamanAktif(Anggota anggota) {
        int total = 0;
        for (int i = 0; i < jumlahPeminjaman; i++) {
            Peminjaman pj = daftarPeminjaman[i];
            if (pj.getAnggota() == anggota && !pj.isSudahDikembalikan()) {
                total++;
            }
        }
        return total;
    }

    // ===== Transaksi Peminjaman =====
    public Peminjaman pinjamPustaka(String kodePustaka, String idAnggota, LocalDate tanggalPinjam)
            throws DataTidakDitemukanException, StokTidakCukupException, BatasPinjamException {

        Pustaka pustaka = cariPustaka(kodePustaka);
        Anggota anggota = cariAnggota(idAnggota);

        // Seleksi: cek batas maksimal pinjam
        if (hitungPeminjamanAktif(anggota) >= anggota.getMaksimalPinjam()) {
            throw new BatasPinjamException(anggota.getNama()
                    + " sudah mencapai batas maksimal pinjam (" + anggota.getMaksimalPinjam() + ").");
        }

        // Seleksi + Polymorphism: cek stok hanya berlaku untuk BukuFisik
        if (pustaka instanceof BukuFisik) {
            BukuFisik bukuFisik = (BukuFisik) pustaka;
            if (bukuFisik.getStok() <= 0) {
                throw new StokTidakCukupException("Stok buku \"" + bukuFisik.getJudul() + "\" habis.");
            }
            bukuFisik.kurangiStok();
        }
        // Jika EBook, tidak perlu cek stok karena bisa diakses berkali-kali.

        Peminjaman peminjaman = new Peminjaman(anggota, pustaka, tanggalPinjam);
        daftarPeminjaman[jumlahPeminjaman] = peminjaman;
        jumlahPeminjaman++;

        return peminjaman;
    }

    // ===== Transaksi Pengembalian =====
    public Denda kembalikanPustaka(String idPeminjaman, LocalDate tanggalKembali)
            throws DataTidakDitemukanException {

        Peminjaman peminjaman = null;
        for (int i = 0; i < jumlahPeminjaman; i++) {
            if (daftarPeminjaman[i].getIdPeminjaman().equalsIgnoreCase(idPeminjaman)) {
                peminjaman = daftarPeminjaman[i];
                break;
            }
        }

        if (peminjaman == null) {
            throw new DataTidakDitemukanException("Peminjaman dengan id '" + idPeminjaman + "' tidak ditemukan.");
        }

        if (peminjaman.isSudahDikembalikan()) {
            throw new DataTidakDitemukanException("Peminjaman ini sudah dikembalikan sebelumnya.");
        }

        peminjaman.kembalikan(tanggalKembali);

        // Seleksi + Polymorphism: kembalikan stok hanya untuk BukuFisik
        Pustaka pustaka = peminjaman.getPustaka();
        if (pustaka instanceof BukuFisik) {
            ((BukuFisik) pustaka).tambahStok();
        }

        Denda denda = new Denda(peminjaman);
        if (denda.getTotalDenda() > 0) {
            daftarDenda[jumlahDenda] = denda;
            jumlahDenda++;
        }

        return denda;
    }

    public int getJumlahPustaka() {
        return jumlahPustaka;
    }

    public int getJumlahAnggota() {
        return jumlahAnggota;
    }
}
