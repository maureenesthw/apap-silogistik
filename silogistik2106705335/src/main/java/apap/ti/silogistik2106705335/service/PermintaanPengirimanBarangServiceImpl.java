package apap.ti.silogistik2106705335.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106705335.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106705335.repository.PermintaanPengirimanBarangDb;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanPengirimanBarang) {
        permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);
    }

    @Override
    public List<PermintaanPengirimanBarang> getAllPermintaanPengirimanBarang() {
        return permintaanPengirimanBarangDb.findAll();
    }

    @Override
    public PermintaanPengirimanBarang getPermintaanPengirimanBarangById(Long idPermintaanPengirimanBarang) {
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : getAllPermintaanPengirimanBarang()) {
            if (permintaanPengirimanBarang.getId().equals(idPermintaanPengirimanBarang)) {
                return permintaanPengirimanBarang;
            }
        }
        return null;
    }

}
