package apap.ti.silogistik2106705335.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {

    private String tipeBarang;

    private String merk;

    private Long hargaBarang;

}
