package apap.ti.silogistik2106705335.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {

    private String sku;

    private Integer tipeBarang;

    private String merk;

    private Long hargaBarang;

    private Integer totalStok;

}
