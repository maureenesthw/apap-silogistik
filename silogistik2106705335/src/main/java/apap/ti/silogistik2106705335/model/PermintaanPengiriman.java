package apap.ti.silogistik2106705335.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "nomor_pengiriman")
    private String nomorPengiriman;

    @NotNull
    @Column(name = "is_cancelled")
    private boolean isCancelled = false;

    @NotNull
    @Column(name = "nama_penerima")
    private String namaPenerima;

    @NotNull
    @Column(name = "alamat_penerima")
    private String alamatPenerima;

    @NotNull
    @Column(name = "tanggal_pengiriman")
    private Date tanggalPengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman")
    private Integer biayaPengiriman;

    @NotNull
    @Column(name = "jenis_layanan")
    private int jenisLayanan; // 1 = Same Day, 2 = Kilat, 3 = Reguler, 4 = Hemat

    @NotNull
    @Temporal(TemporalType.TIMESTAMP) // Use TIMESTAMP for DateTime
    @Column(name = "waktu_permintaan")
    private Date waktuPermintaan;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_karyawan", nullable = false)
    @JsonIgnore
    private Karyawan karyawan;

    @JsonIgnore
    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

    // taro di service
    // public PermintaanPengiriman(@NotNull boolean isCancelled, @NotNull String
    // namaPenerima,
    // @NotNull String alamatPenerima, @NotNull Date tanggalPengiriman, @NotNull
    // Integer biayaPengiriman,
    // @NotNull int jenisLayanan, long idKaryawan) {
    // this.isCancelled = isCancelled;
    // this.namaPenerima = namaPenerima;
    // this.alamatPenerima = alamatPenerima;
    // this.tanggalPengiriman = tanggalPengiriman;
    // this.biayaPengiriman = biayaPengiriman;
    // this.jenisLayanan = jenisLayanan;
    // this.idKaryawan = idKaryawan;

    // // custom
    // this.waktuPermintaan = new Date();
    // // TODO
    // int jumlahBarangDipesan = 1;
    // this.nomorPengiriman = generateNoPengiriman(jumlahBarangDipesan,
    // this.jenisLayanan, this.waktuPermintaan);
    // }

    // private String generateNoPengiriman(int jumlahBarangDipesan, int
    // jenisLayanan, Date waktuPermintaan) {

    // int lastTwoDigits = jumlahBarangDipesan % 100;
    // String jumlahBarang = String.format("%02d", lastTwoDigits);

    // // Convert jenisLayanan to its corresponding code
    // String layananCode;
    // switch (jenisLayanan) {
    // case 1:
    // layananCode = "SAM";
    // break;
    // case 2:
    // layananCode = "KIL";
    // break;
    // case 3:
    // layananCode = "REG";
    // break;
    // case 4:
    // layananCode = "HEM";
    // break;
    // default:
    // throw new IllegalArgumentException("Invalid jenisLayanan");
    // }

    // // Get the current time in HH:mm:ss format
    // String timeStamp = new SimpleDateFormat("HH:mm:ss").format(waktuPermintaan);

    // // Combine
    // String noPengiriman = "REQ" + jumlahBarang + layananCode + timeStamp;

    // return noPengiriman;
    // }
}
