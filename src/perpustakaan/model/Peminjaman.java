package perpustakaan.model;

import java.time.LocalDate;

/**
 * Class Peminjaman mencatat transaksi peminjaman sebuah Pustaka
 * oleh seorang Anggota.
 */
public class Peminjaman {

    private static int counter = 0;

    private String idPeminjaman;
    private Anggota anggota;
    private Pustaka pustaka;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembaliRencana;
    private LocalDate tanggalKembaliAktual;
    private boolean sudahDikembalikan;

    public Peminjaman(Anggota anggota, Pustaka pustaka, LocalDate tanggalPinjam) {
        counter++;
        this.idPeminjaman = "PJM" + String.format("%03d", counter);
        this.anggota = anggota;
        this.pustaka = pustaka;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembaliRencana = tanggalPinjam.plusDays(7);
        this.tanggalKembaliAktual = null;
        this.sudahDikembalikan = false;
    }

    // Accessor
    public String getIdPeminjaman() {
        return idPeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Pustaka getPustaka() {
        return pustaka;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalKembaliRencana() {
        return tanggalKembaliRencana;
    }

    public LocalDate getTanggalKembaliAktual() {
        return tanggalKembaliAktual;
    }

    public boolean isSudahDikembalikan() {
        return sudahDikembalikan;
    }

    // Mutator
    public void setTanggalKembaliAktual(LocalDate tanggalKembaliAktual) {
        this.tanggalKembaliAktual = tanggalKembaliAktual;
    }

    public void setSudahDikembalikan(boolean sudahDikembalikan) {
        this.sudahDikembalikan = sudahDikembalikan;
    }

    public void kembalikan(LocalDate tanggalAktual) {
        this.tanggalKembaliAktual = tanggalAktual;
        this.sudahDikembalikan = true;
    }

    @Override
    public String toString() {
        String status = sudahDikembalikan
                ? "Sudah dikembalikan (" + tanggalKembaliAktual + ")"
                : "Masih dipinjam";
        return "[" + idPeminjaman + "] " + anggota.getNama() + " meminjam \""
                + pustaka.getJudul() + "\" | Pinjam: " + tanggalPinjam
                + " | Batas: " + tanggalKembaliRencana + " | Status: " + status;
    }
}
