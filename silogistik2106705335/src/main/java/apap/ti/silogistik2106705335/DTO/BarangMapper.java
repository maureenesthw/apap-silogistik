package apap.ti.silogistik2106705335.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apap.ti.silogistik2106705335.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106705335.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106705335.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106705335.model.Barang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    @Mapping(target = "tipeBarang", ignore = true)
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);

    @Mapping(target = "totalStok", ignore = true)
    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);

    @Mapping(target = "tipeBarang", ignore = true)
    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    @Mapping(target = "tipeBarang", ignore = true)
    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);
}
