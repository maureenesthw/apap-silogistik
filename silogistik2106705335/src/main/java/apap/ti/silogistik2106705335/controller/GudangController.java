package apap.ti.silogistik2106705335.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106705335.DTO.GudangBarangMapper;
import apap.ti.silogistik2106705335.DTO.GudangMapper;
import apap.ti.silogistik2106705335.model.Barang;
import apap.ti.silogistik2106705335.model.Gudang;
import apap.ti.silogistik2106705335.model.GudangBarang;
import apap.ti.silogistik2106705335.service.BarangService;
import apap.ti.silogistik2106705335.service.GudangBarangService;
import apap.ti.silogistik2106705335.service.GudangService;

@Controller
public class GudangController {

    @Autowired
    GudangService gudangService;

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    GudangBarangService gudangBarangService;

    @Autowired
    GudangBarangMapper gudangBarangMapper;

    @Autowired
    BarangService barangService;

    @GetMapping(value = "/gudang")
    public String listGudang(Model model) {
        var listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);
        return "gudang";
    }

    @GetMapping(value = "/gudang/{id}")
    public String detailGudang(@PathVariable("id") Long idGudang, Model model) {

        Gudang gudang = gudangService.getGudangById(idGudang);

        model.addAttribute("gudang", gudang);
        return "gudang-detail";
    }

    @GetMapping(value = "/gudang/cari-barang")
    public String cariBarang(@RequestParam(value = "sku", required = false) String sku, Model model) {
        // Get gudang with the barang searched
        List<GudangBarang> listGudangBarang = gudangBarangService.getAllGudangBarang();
        if (sku != null) {
            listGudangBarang = gudangBarangService.getAllGudangBarangBySku(sku);
        }

        // Get all barang available
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listGudangBarang", listGudangBarang);
        return "gudang-cari-barang";
    }
}
