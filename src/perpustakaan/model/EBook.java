package perpustakaan.model;

/**
 * EBook adalah turunan (Inheritance) dari Pustaka.
 * Merepresentasikan buku digital yang bisa diunduh, tidak punya stok fisik.
 */
public class EBook extends Pustaka {

    private double ukuranFileMB;
    private String linkUnduh;

    public EBook(String kodePustaka, String judul, String pengarang,
                 int tahunTerbit, double ukuranFileMB, String linkUnduh) {
        super(kodePustaka, judul, pengarang, tahunTerbit);
        this.ukuranFileMB = ukuranFileMB;
        this.linkUnduh = linkUnduh;
    }

    // Accessor
    public double getUkuranFileMB() {
        return ukuranFileMB;
    }

    public String getLinkUnduh() {
        return linkUnduh;
    }

    // Mutator
    public void setUkuranFileMB(double ukuranFileMB) {
        this.ukuranFileMB = ukuranFileMB;
    }

    public void setLinkUnduh(String linkUnduh) {
        this.linkUnduh = linkUnduh;
    }

    // Polymorphism: implementasi berbeda dari BukuFisik
    @Override
    public String aksesPustaka() {
        return "E-Book dapat diakses dengan mengunduh dari: " + linkUnduh;
    }

    @Override
    public String getJenisPustaka() {
        return "E-Book";
    }

    @Override
    public String toString() {
        return super.toString() + " | Jenis: " + getJenisPustaka()
                + " | Ukuran: " + ukuranFileMB + " MB";
    }
}
