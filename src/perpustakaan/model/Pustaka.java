package perpustakaan.model;

/**
 * Class abstrak Pustaka merupakan superclass dari BukuFisik dan EBook.
 * Menyimpan atribut umum yang dimiliki oleh semua jenis pustaka
 * dan menerapkan enkapsulasi (semua atribut private, diakses lewat
 * accessor/mutator).
 */
public abstract class Pustaka {

    // ===== Atribut (private -> Encapsulation) =====
    private String kodePustaka;
    private String judul;
    private String pengarang;
    private int tahunTerbit;

    // ===== Constructor =====
    public Pustaka(String kodePustaka, String judul, String pengarang, int tahunTerbit) {
        this.kodePustaka = kodePustaka;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
    }

    // ===== Accessor (getter) =====
    public String getKodePustaka() {
        return kodePustaka;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    // ===== Mutator (setter) =====
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    /**
     * Method abstrak, wajib diimplementasikan berbeda oleh setiap
     * turunan Pustaka (dasar untuk Polymorphism di BukuFisik & EBook).
     */
    public abstract String aksesPustaka();

    /**
     * Method abstrak untuk mengetahui jenis pustaka.
     */
    public abstract String getJenisPustaka();

    @Override
    public String toString() {
        return "[" + kodePustaka + "] " + judul + " - " + pengarang + " (" + tahunTerbit + ")";
    }
}
