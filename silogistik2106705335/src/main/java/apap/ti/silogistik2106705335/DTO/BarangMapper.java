package apap.ti.silogistik2106705335.DTO;

import apap.ti.silogistik2106705335.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106705335.model.Barang;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
}
