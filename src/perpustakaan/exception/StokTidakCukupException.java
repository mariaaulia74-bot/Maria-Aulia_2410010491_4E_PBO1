package perpustakaan.exception;

/**
 * Dilempar saat stok BukuFisik tidak mencukupi untuk dipinjam.
 */
public class StokTidakCukupException extends Exception {
    public StokTidakCukupException(String pesan) {
        super(pesan);
    }
}
