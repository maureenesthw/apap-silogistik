package apap.ti.silogistik2106705335.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import apap.ti.silogistik2106705335.service.BarangService;
import apap.ti.silogistik2106705335.service.GudangService;
import apap.ti.silogistik2106705335.service.KaryawanService;
import apap.ti.silogistik2106705335.service.PermintaanPengirimanService;

@Controller
public class BerandaController {

    @Autowired
    GudangService gudangService;

    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @GetMapping("/")
    public String home(Model model) {
        int jumlahGudang = gudangService.getAllGudang().size();
        int jumlahBarang = barangService.getAllBarang().size();
        int jumlahPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman().size();
        int jumlahKaryawan = karyawanService.getAllKaryawan().size();

        model.addAttribute("jumlahGudang", jumlahGudang);
        model.addAttribute("jumlahBarang", jumlahBarang);
        model.addAttribute("jumlahPermintaanPengiriman", jumlahPermintaanPengiriman);
        model.addAttribute("jumlahKaryawan", jumlahKaryawan);

        return "home";
    }

}
