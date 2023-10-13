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

    @Size(max = 16)
    @Column(name = "nomor_pengiriman", nullable = false)
    private String nomorPengiriman;

    @NotNull
    @Column(name = "is_cancelled")
    private boolean isCancelled = false;

    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @Column(name = "tanggal_pengiriman", nullable = false)
    private Date tanggalPengiriman;

    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;

    @Column(name = "jenis_layanan", nullable = false)
    private int jenisLayanan; // 1 = Same Day, 2 = Kilat, 3 = Reguler, 4 = Hemat

    @Temporal(TemporalType.TIMESTAMP) // Use TIMESTAMP for DateTime
    @Column(name = "waktu_permintaan", nullable = false)
    private Date waktuPermintaan;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_karyawan", nullable = false)
    @JsonIgnore
    private Karyawan karyawan;

    @JsonIgnore
    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

}
