package apap.ti.silogistik2106705335.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106705335.DTO.PermintaanPengirimanMapper;
import apap.ti.silogistik2106705335.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106705335.model.Barang;
import apap.ti.silogistik2106705335.model.Karyawan;
import apap.ti.silogistik2106705335.model.PermintaanPengiriman;
import apap.ti.silogistik2106705335.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106705335.service.BarangService;
import apap.ti.silogistik2106705335.service.KaryawanService;
import apap.ti.silogistik2106705335.service.PermintaanPengirimanService;

@Controller
public class PermintaanPengirimanController {

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @GetMapping(value = "/permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        return "permintaan-pengiriman";
    }

    @GetMapping(value = "/permintaan-pengiriman/{id}")
    public String detailPermintaanPengiriman(@PathVariable("id") Long id, Model model) {

        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);

        String jenisLayanan = mapJenisLayananCodeToValue(permintaanPengiriman.getJenisLayanan());
        model.addAttribute("jenisLayanan", jenisLayanan);

        return "permintaan-pengiriman-detail";
    }

    @GetMapping(value = "/permintaan-pengiriman/tambah")
    public String formTambahPermintaanPengiriman(Model model) {
        // Membuat DTO untuk isian form
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

        // List Karyawan untuk dropdown
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawan", listKaryawan);

        return "permintaan-pengiriman-tambah";
    }

    @PostMapping(value = "/permintaan-pengiriman/tambah", params = { "addRow" })
    public String addRowTambahPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO pengirimanDTO,
            Model model) {

        if (pengirimanDTO.getListPermintaanPengirimanBarang() == null
                || pengirimanDTO.getListPermintaanPengirimanBarang().size() == 0) {
            pengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }

        // Add a new PermintaanPengirimanBarang
        pengirimanDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());

        // to be parsed in html
        model.addAttribute("permintaanPengirimanDTO", pengirimanDTO);
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawan", listKaryawan);
        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);

        return "permintaan-pengiriman-tambah";
    }

    @PostMapping(value = "/permintaan-pengiriman/tambah")
    public String tambahPermintaanPengiriman(
            @ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, Model model) {
        
        var permintaanPengiriman = permintaanPengirimanMapper
                .createPermintaanPengirimanRequestDTOToPermintaanPengiriman(createPermintaanPengirimanRequestDTO);
        
                // Convert tanggal Pengiriman from string to date type
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date tanggalPengiriman = dateFormat.parse(createPermintaanPengirimanRequestDTO.getTanggalPengiriman());
            permintaanPengiriman.setTanggalPengiriman(tanggalPengiriman);
        } catch (ParseException e) {
        }
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListPermintaanPengirimanBarang()) {
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
        }
        permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);

        String message = "Permintaan Pengiriman berhasil dibuat";
        model.addAttribute("page", "permintaan-pengiriman");
        model.addAttribute("message", message);
        return "success";
    }

    private String mapJenisLayananCodeToValue(int jenisLayananCode) {
        String jenisLayanan;
        switch (jenisLayananCode) {
            case 1:
                jenisLayanan = "Same Day";
                break;
            case 2:
                jenisLayanan = "Kilat";
                break;
            case 3:
                jenisLayanan = "Reguler";
                break;
            case 4:
                jenisLayanan = "Hemat";
                break;
            default:
                jenisLayanan = "Same Day";
                break;
        }
        return jenisLayanan;
    }
    

}
