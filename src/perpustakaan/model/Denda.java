package perpustakaan.model;

import java.time.temporal.ChronoUnit;

/**
 * Class Denda menghitung denda keterlambatan berdasarkan sebuah Peminjaman
 * yang sudah dikembalikan.
 */
public class Denda {

    private static final double TARIF_PER_HARI = 2000.0;

    private Peminjaman peminjaman;
    private long hariTerlambat;
    private double totalDenda;

    public Denda(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
        hitungDenda();
    }

    // Perhitungan denda: seleksi + perulangan implisit lewat ChronoUnit
    public void hitungDenda() {
        if (!peminjaman.isSudahDikembalikan() || peminjaman.getTanggalKembaliAktual() == null) {
            this.hariTerlambat = 0;
            this.totalDenda = 0;
            return;
        }

        long selisih = ChronoUnit.DAYS.between(
                peminjaman.getTanggalKembaliRencana(),
                peminjaman.getTanggalKembaliAktual());

        if (selisih > 0) {
            this.hariTerlambat = selisih;
            this.totalDenda = selisih * TARIF_PER_HARI;
        } else {
            this.hariTerlambat = 0;
            this.totalDenda = 0;
        }
    }

    // Accessor
    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public long getHariTerlambat() {
        return hariTerlambat;
    }

    public double getTotalDenda() {
        return totalDenda;
    }

    @Override
    public String toString() {
        return "[" + peminjaman.getIdPeminjaman() + "] " + peminjaman.getAnggota().getNama()
                + " | Terlambat: " + hariTerlambat + " hari"
                + " | Denda: Rp" + String.format("%,.0f", totalDenda);
    }
}
