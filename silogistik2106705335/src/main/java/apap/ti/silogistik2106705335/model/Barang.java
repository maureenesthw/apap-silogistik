package apap.ti.silogistik2106705335.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {

    @Id
    @Column(nullable = false)
    private String sku;

    @NotNull
    @Column(name = "tipe_barang")
    private Integer tipeBarang; // 1 = Produk Elektronik, 2 = Pakaian & Aksesoris,
                                // 3=Makanan&Minuman,4=Kosmetik,5=Perlengkapan Rumah

    @NotNull
    @Column(name = "merk")
    private String merk;

    @NotNull
    @Column(name = "harga_barang")
    private Long hargaBarang;

    @JsonIgnore
    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;

    @JsonIgnore
    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

}
