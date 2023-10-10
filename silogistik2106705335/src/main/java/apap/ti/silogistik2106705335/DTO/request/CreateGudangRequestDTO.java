package apap.ti.silogistik2106705335.DTO.request;

import java.util.List;

import apap.ti.silogistik2106705335.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {

    private String nama;

    private String alamatGudang;

    private List<GudangBarang> listGudangBarang;

}
