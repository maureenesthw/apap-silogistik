package apap.ti.silogistik2106705335.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apap.ti.silogistik2106705335.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106705335.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    
    @Mapping(target = "tanggalPengiriman", ignore = true)
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
}
