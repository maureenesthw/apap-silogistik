package apap.ti.silogistik2106705335.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106705335.model.Barang;
import apap.ti.silogistik2106705335.repository.BarangDb;

@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    BarangDb barangDb;

    @Override
    public void saveBarang(Barang barang) {
        barang.setSku(generateSKU(barang.getTipeBarang()));
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public Barang getBarangBySku(String skuBarang) {
        for (Barang barang : getAllBarang()) {
            if (barang.getSku().equals(skuBarang)) {
                return barang;
            }
        }
        return null;
    }

    @Override
    public Barang updateBarang(Barang barangFromDto) {
        Barang barang = getBarangBySku(barangFromDto.getSku());
        if (barang != null) {
            barang.setMerk(barangFromDto.getMerk());
            barang.setTipeBarang(barangFromDto.getTipeBarang());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

    private String generateSKU(Integer tipeBarang) {
        String typePrefix;

        switch (tipeBarang) {
            case 1:
                typePrefix = "ELEC";
                break;
            case 2:
                typePrefix = "CLOT";
                break;
            case 3:
                typePrefix = "FOOD";
                break;
            case 4:
                typePrefix = "COSM";
                break;
            case 5:
                typePrefix = "TOOL";
                break;
            default:
                throw new IllegalArgumentException("Invalid tipe_barang value");
        }

        String count = String.format("%03d", barangDb.count() + 1L);

        return typePrefix + count;
    }
}
