package apap.ti.silogistik2106705335.service;

import java.util.List;

import apap.ti.silogistik2106705335.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {

    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    PermintaanPengiriman getPermintaanPengirimanById(Long idPermintaanPengiriman);

    PermintaanPengiriman cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

}
