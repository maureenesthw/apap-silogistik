package apap.ti.silogistik2106705335.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106705335.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106705335.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {

    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
    
}
