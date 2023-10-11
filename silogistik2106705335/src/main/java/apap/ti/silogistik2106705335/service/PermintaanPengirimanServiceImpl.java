package apap.ti.silogistik2106705335.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106705335.model.PermintaanPengiriman;
import apap.ti.silogistik2106705335.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106705335.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106705335.repository.PermintaanPengirimanDb;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {

    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        //TODO
        Date waktuPermintaan = new Date();
        int jumlahBarang = countJumlahBarang(permintaanPengiriman.getListPermintaanPengirimanBarang());
        permintaanPengiriman.setWaktuPermintaan(waktuPermintaan);
        permintaanPengiriman.setNomorPengiriman(generateNomorPengiriman(jumlahBarang, permintaanPengiriman.getJenisLayanan(), waktuPermintaan));
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAll();
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long idPermintaanPengiriman) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getId().equals(idPermintaanPengiriman)) {
                return permintaanPengiriman;
            }
        }
        return null;
    }

    private int countJumlahBarang(List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang) {
        int jumlahBarang = 0;
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : listPermintaanPengirimanBarang) {
            jumlahBarang += permintaanPengirimanBarang.getKuantitasPengiriman();
        }
        return jumlahBarang;
    }


    private String generateNomorPengiriman(int jumlahBarang, int jenisLayanan, Date waktuPermintaan) {
        // Ensure the jumlahBarang is a 2-digit number
        String jumlahBarangStr = String.format("%02d", jumlahBarang % 100);

        // Map jenisLayanan to its respective code
        String jenisLayananCode = "";
        switch (jenisLayanan) {
            case 1:
                jenisLayananCode = "SAM";
                break;
            case 2:
                jenisLayananCode = "KIL";
                break;
            case 3:
                jenisLayananCode = "REG";
                break;
            case 4:
                jenisLayananCode = "HEM";
                break;
            default:
                // Handle invalid jenisLayanan here if necessary
                break;
        }

        // Get the current time in HH:mm:ss format
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String waktuPembuatan = timeFormat.format(new Date());

        // Combine the parts to create the unique nomor pengiriman
        String nomorPengiriman = "REQ" + jumlahBarangStr + jenisLayananCode + waktuPembuatan;

        return nomorPengiriman;
    }


}
