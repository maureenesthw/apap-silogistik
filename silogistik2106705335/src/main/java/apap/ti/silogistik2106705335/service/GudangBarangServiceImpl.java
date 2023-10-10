package apap.ti.silogistik2106705335.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106705335.model.GudangBarang;
import apap.ti.silogistik2106705335.repository.GudangBarangDb;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void saveGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDb.save(gudangBarang);
    }

    @Override
    public List<GudangBarang> getAllGudangBarang() {
        return gudangBarangDb.findAll();
    }

    @Override
    public GudangBarang getGudangBarangById(Long idGudangBarang) {
        for (GudangBarang gudangBarang : getAllGudangBarang()) {
            if (gudangBarang.getId().equals(idGudangBarang)) {
                return gudangBarang;
            }
        }
        return null;
    }
    
}
