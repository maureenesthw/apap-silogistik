package apap.ti.silogistik2106705335.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106705335.model.Barang;
import apap.ti.silogistik2106705335.model.Gudang;
import apap.ti.silogistik2106705335.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService {
    
    @Autowired
    GudangDb gudangDb;

    @Override
    public void saveGudang(Gudang gudang) {
        gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(Long idGudang) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getId().equals(idGudang)) {
                return gudang;
            }
        }
        return null;
    }

    @Override
    public Gudang updateGudang(Gudang gudangFromDTO) {
        Gudang gudang = getGudangById(gudangFromDTO.getId());
        if (gudang != null) {
            gudang.setListGudangBarang(gudangFromDTO.getListGudangBarang());
            gudangDb.save(gudang);
        }
        return gudang;
    }
    
}
