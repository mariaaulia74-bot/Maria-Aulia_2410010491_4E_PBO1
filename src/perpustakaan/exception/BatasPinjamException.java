package perpustakaan.exception;

/**
 * Dilempar saat seorang Anggota sudah mencapai batas maksimal
 * jumlah peminjaman aktif yang diperbolehkan.
 */
public class BatasPinjamException extends Exception {
    public BatasPinjamException(String pesan) {
        super(pesan);
    }
}
