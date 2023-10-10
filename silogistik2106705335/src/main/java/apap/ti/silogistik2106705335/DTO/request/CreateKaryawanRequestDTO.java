package apap.ti.silogistik2106705335.DTO.request;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106705335.model.PermintaanPengiriman;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateKaryawanRequestDTO {

    private String nama;

    private Integer jenisKelamin;

    private Date tanggalLahir;

    private List<PermintaanPengiriman> listPermintaanPengiriman;

}
