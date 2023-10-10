package apap.ti.silogistik2106705335.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gudang_barang")
public class GudangBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_gudang", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Gudang gudang;

    @ManyToOne
    @JoinColumn(name = "sku_barang", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Barang barang;

    @Column(name = "stok", nullable = false)
    private int stok;

}
