package perpustakaan.model;

/**
 * BukuFisik adalah turunan (Inheritance) dari Pustaka.
 * Merepresentasikan buku cetak yang memiliki stok fisik di rak.
 */
public class BukuFisik extends Pustaka {

    private int stok;
    private String lokasiRak;

    public BukuFisik(String kodePustaka, String judul, String pengarang,
                      int tahunTerbit, int stok, String lokasiRak) {
        super(kodePustaka, judul, pengarang, tahunTerbit);
        this.stok = stok;
        this.lokasiRak = lokasiRak;
    }

    // Accessor
    public int getStok() {
        return stok;
    }

    public String getLokasiRak() {
        return lokasiRak;
    }

    // Mutator
    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setLokasiRak(String lokasiRak) {
        this.lokasiRak = lokasiRak;
    }

    public void kurangiStok() {
        this.stok--;
    }

    public void tambahStok() {
        this.stok++;
    }

    // Polymorphism: implementasi berbeda dari EBook
    @Override
    public String aksesPustaka() {
        return "Buku fisik dapat dipinjam langsung di lokasi rak: " + lokasiRak;
    }

    @Override
    public String getJenisPustaka() {
        return "Buku Fisik";
    }

    @Override
    public String toString() {
        return super.toString() + " | Jenis: " + getJenisPustaka()
                + " | Stok: " + stok + " | Rak: " + lokasiRak;
    }
}
