package apap.ti.silogistik2106705335.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106705335.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106705335.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {

    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);

}
