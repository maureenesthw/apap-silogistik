package apap.ti.silogistik2106705335.DTO;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106705335.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106705335.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {

    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
    

    // Function untuk mengonversi entity Buku menjadi ReadBukuResponseDTO
    // @Mapping(target = "namaPenerbit", ignore = true)
    // ReadBukuResponseDTO bukuToReadBukuResponseDTO(Buku buku);

    // Menggunakan @AfterMapping untuk mengisi properti namaPenerbit
    // @AfterMapping
    // default void addNamaPenerbit(@MappingTarget ReadBukuResponseDTO responseDTO, Buku buku) {
    //     if (buku.getPenerbit() != null) {
    //         responseDTO.setNamaPenerbit(buku.getPenerbit().getNamaPenerbit());
    //     }
    // }
}
