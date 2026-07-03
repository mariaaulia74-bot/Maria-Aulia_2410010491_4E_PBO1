package perpustakaan.exception;

/**
 * Dilempar saat data Pustaka, Anggota, atau Peminjaman yang dicari
 * tidak ditemukan di dalam sistem.
 */
public class DataTidakDitemukanException extends Exception {
    public DataTidakDitemukanException(String pesan) {
        super(pesan);
    }
}
