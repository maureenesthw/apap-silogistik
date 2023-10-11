package apap.ti.silogistik2106705335.DTO;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106705335.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106705335.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106705335.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {

    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);

    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);

    // Function untuk mengonversi entity Buku menjadi ReadBukuResponseDTO
    // @Mapping(target = "namaPenerbit", ignore = true)
    // ReadBukuResponseDTO bukuToReadBukuResponseDTO(Buku buku);

    // Menggunakan @AfterMapping untuk mengisi properti namaPenerbit
    // @AfterMapping
    // default void addNamaPenerbit(@MappingTarget ReadBukuResponseDTO responseDTO,
    // Buku buku) {
    // if (buku.getPenerbit() != null) {
    // responseDTO.setNamaPenerbit(buku.getPenerbit().getNamaPenerbit());
    // }
    // }
}
