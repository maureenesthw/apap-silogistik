package apap.ti.silogistik2106705335.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106705335.DTO.GudangBarangMapper;
import apap.ti.silogistik2106705335.DTO.GudangMapper;
import apap.ti.silogistik2106705335.DTO.request.UpdateGudangRequestDTO;
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
        List<GudangBarang> listGudangBarang = null;
        if (sku != null) {
            listGudangBarang = gudangBarangService.getAllGudangBarangBySku(sku);
            model.addAttribute("sku", sku);
        }

        // Get all barang available
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listGudangBarang", listGudangBarang);
        return "gudang-cari-barang";
    }

    @GetMapping(value = "/gudang/{id}/restock-barang")
    public String formRestockBarang(@PathVariable("id") Long id, Model model) {
        var gudang = gudangService.getGudangById(id);
        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("gudangDTO", gudangDTO);
        return "gudang-restock";
    }

    @PostMapping(value = "/gudang/{id}/restock-barang", params = { "addRow" })
    public String addRowRestockBarang(@PathVariable("id") Long id,
            @ModelAttribute UpdateGudangRequestDTO gudangDTO, Model model) {

        if (gudangDTO.getListGudangBarang() == null
                || gudangDTO.getListGudangBarang().size() == 0) {
            gudangDTO.setListGudangBarang(new ArrayList<>());
        }

        // Add a new GudangBarang with the correct id_gudang
        GudangBarang newGudangBarang = new GudangBarang();
        newGudangBarang.setGudang(gudangService.getGudangById(id));
        gudangDTO.getListGudangBarang().add(newGudangBarang);

        model.addAttribute("listBarang", barangService.getAllBarang()); // untuk dropdown
        model.addAttribute("gudangDTO", gudangDTO);

        return "gudang-restock";
    }

    @PostMapping(value = "/gudang/{id}/restock-barang")
    public String restockBarang(@PathVariable("id") Long id, @ModelAttribute UpdateGudangRequestDTO gudangDTO,
            Model model) {
        Gudang gudang = gudangService.getGudangById(id);

        // update GudangBarang
        if (gudangDTO.getListGudangBarang() != null) {
            for (GudangBarang gudangBarang : gudangDTO.getListGudangBarang()) {
                gudangBarang.setGudang(gudang);
            }
        }

        var gudangFromDTO = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);
        gudangService.updateGudang(gudangFromDTO);
        model.addAttribute("message", "Restock gudang berhasil dilakukan");
        model.addAttribute("page", "gudang");
        return "success";
    }
}
