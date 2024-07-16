package apap.ti.silogistik2106705335.service;

import java.util.List;

import apap.ti.silogistik2106705335.model.Barang;

public interface BarangService {
    
    void saveBarang(Barang barang);

    List<Barang> getAllBarang();

    Barang getBarangBySku(String skuBarang);

    Barang updateBarang(Barang barangFromDto);

    void deleteBarang(Barang barang);
    
}
