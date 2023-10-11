package apap.ti.silogistik2106705335.DTO.request;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106705335.model.Karyawan;
import apap.ti.silogistik2106705335.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {

    private String namaPenerima;

    private String alamatPenerima;

    private String tanggalPengiriman;

    private Integer biayaPengiriman;

    private int jenisLayanan; // 1 = Same Day, 2 = Kilat, 3 = Reguler, 4 = Hemat

    private Date waktuPermintaan;

    private Karyawan karyawan;

    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

}
