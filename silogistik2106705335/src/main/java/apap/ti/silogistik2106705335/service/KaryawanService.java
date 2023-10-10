package apap.ti.silogistik2106705335.service;

import java.util.List;

import apap.ti.silogistik2106705335.model.Karyawan;

public interface KaryawanService {

    void saveKaryawan(Karyawan karyawan);

    List<Karyawan> getAllKaryawan();

    Karyawan getKaryawanById(Long idKaryawan);
    
}

