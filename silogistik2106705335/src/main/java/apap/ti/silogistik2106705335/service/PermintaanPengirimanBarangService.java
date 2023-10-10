package apap.ti.silogistik2106705335.service;

import java.util.List;

import apap.ti.silogistik2106705335.model.PermintaanPengirimanBarang;

public interface PermintaanPengirimanBarangService {

    void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanPengirimanBarang);

    List<PermintaanPengirimanBarang> getAllPermintaanPengirimanBarang();

    PermintaanPengirimanBarang getPermintaanPengirimanBarangById(Long idPermintaanPengirimanBarang);

}
