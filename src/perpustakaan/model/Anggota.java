package perpustakaan.model;

/**
 * Class Anggota merepresentasikan anggota perpustakaan.
 * Batas maksimal pinjam ditentukan berdasarkan jenis anggota
 * (contoh penerapan Seleksi di dalam constructor).
 */
public class Anggota {

    private String idAnggota;
    private String nama;
    private String jenisAnggota; // "Mahasiswa" atau "Dosen"
    private int maksimalPinjam;

    public Anggota(String idAnggota, String nama, String jenisAnggota) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.jenisAnggota = jenisAnggota;

        // Seleksi: batas pinjam berbeda tergantung jenis anggota
        if (jenisAnggota.equalsIgnoreCase("Dosen")) {
            this.maksimalPinjam = 5;
        } else {
            this.maksimalPinjam = 3;
        }
    }

    // Accessor
    public String getIdAnggota() {
        return idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisAnggota() {
        return jenisAnggota;
    }

    public int getMaksimalPinjam() {
        return maksimalPinjam;
    }

    // Mutator
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisAnggota(String jenisAnggota) {
        this.jenisAnggota = jenisAnggota;
    }

    @Override
    public String toString() {
        return "[" + idAnggota + "] " + nama + " (" + jenisAnggota
                + ") - Maks. Pinjam: " + maksimalPinjam;
    }
}
