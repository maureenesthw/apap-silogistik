package apap.ti.silogistik2106705335.DTO.request;

import java.util.Date;

import apap.ti.silogistik2106705335.model.Karyawan;

public class CreatePermintaanPengirimanRequestDTO {

    private String namaPenerima;

    private String alamatPenerima;

    private Date tanggalPengiriman;

    private Integer biayaPengiriman;

    private int jenisLayanan; // 1 = Same Day, 2 = Kilat, 3 = Reguler, 4 = Hemat

    private Date waktuPermintaan;

    private Karyawan karyawan;
}
