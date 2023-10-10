package apap.ti.silogistik2106705335.service;

import java.util.List;

import apap.ti.silogistik2106705335.model.Gudang;

public interface GudangService {
    
    void saveGudang(Gudang gudang);

    List<Gudang> getAllGudang();

    Gudang getGudangById(Long idGudang);
    
}
