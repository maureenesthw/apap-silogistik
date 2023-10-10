package apap.ti.silogistik2106705335.service;

import java.util.List;

import apap.ti.silogistik2106705335.model.GudangBarang;

public interface GudangBarangService {

    void saveGudangBarang(GudangBarang gudangBarang);

    List<GudangBarang> getAllGudangBarang();

    GudangBarang getGudangBarangById(Long idGudangBarang);

    List<GudangBarang> getAllGudangBarangBySku(String sku);

}
