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
import org.springframework.web.bind.annotation.RequestMapping;

import apap.ti.silogistik2106705335.DTO.BarangMapper;
import apap.ti.silogistik2106705335.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106705335.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106705335.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106705335.model.Barang;
import apap.ti.silogistik2106705335.model.GudangBarang;
import apap.ti.silogistik2106705335.service.BarangService;

@Controller
public class BarangController {

    @Autowired
    BarangService barangService;

    @Autowired
    BarangMapper barangMapper;

    @GetMapping(value = "/barang")
    public String listBarang(Model model) {
        var listBarang = barangService.getAllBarang();
        List<ReadBarangResponseDTO> listBarangDTO = new ArrayList<>();

        for (Barang barang : listBarang) {
            // Map Barang to ReadBarangResponseDTO
            ReadBarangResponseDTO barangDTO = barangMapper.barangToReadBarangResponseDTO(barang);

            // Count totalStok
            Integer totalStok = countTotalStok(barang);
            barangDTO.setTotalStok(totalStok);

            // add barangDTO to list
            listBarangDTO.add(barangDTO);
        }

        model.addAttribute("listBarangDTO", listBarangDTO);
        return "barang";
    }

    @GetMapping(value = "/barang/tambah")
    public String formAddBarang(Model model) {
        // Membuat DTO baru sebagai isian form pengguna
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);

        // List tipe barang
        List<String> listTipeBarang = new ArrayList<>();
        listTipeBarang.add("Produk Elektronik");
        listTipeBarang.add("Pakaian & Aksesoris");
        listTipeBarang.add("Makanan & Minuman");
        listTipeBarang.add("Kosmetik");
        listTipeBarang.add("Perlengkapan Rumah");
        model.addAttribute("listTipeBarang", listTipeBarang);

        return "barang-tambah";
    }

    @PostMapping(value = "/barang/tambah")
    public String addBarang(@ModelAttribute CreateBarangRequestDTO barangDTO, Model model) {
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);

        // determine tipeBarang
        Integer tipeBarang = getIntegerTipeBarang(barangDTO);
        barang.setTipeBarang(tipeBarang);

        barangService.saveBarang(barang);

        String message = "Barang berhasil dibuat";
        model.addAttribute("page", "barang");
        model.addAttribute("message", message);
        return "success";
    }

    @GetMapping(value = "/barang/{sku}")
    public String detailBarang(@PathVariable("sku") String sku, Model model) {
        Barang barang = barangService.getBarangBySku(sku);

        // get tipe barang in string
        String tipeBarang = getStringTipeBarang(barang);

        // Count total stok
        Integer totalStok = countTotalStok(barang);

        model.addAttribute("barang", barang);
        model.addAttribute("totalStok", totalStok);
        model.addAttribute("tipeBarang", tipeBarang);
        return "barang-detail";
    }

    @GetMapping(value = "/barang/{sku}/ubah")
    public String formUbahBarang(@PathVariable("sku") String sku, Model model) {
        Barang barang = barangService.getBarangBySku(sku);
        UpdateBarangRequestDTO updateBarangRequestDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);
        updateBarangRequestDTO.setTipeBarang(getStringTipeBarang(barang));

        // List tipe barang
        List<String> listTipeBarang = new ArrayList<>();
        listTipeBarang.add("Produk Elektronik");
        listTipeBarang.add("Pakaian & Aksesoris");
        listTipeBarang.add("Makanan & Minuman");
        listTipeBarang.add("Kosmetik");
        listTipeBarang.add("Perlengkapan Rumah");

        model.addAttribute("barangDTO", updateBarangRequestDTO);
        model.addAttribute("listTipeBarang", listTipeBarang);
        return "barang-ubah";
    }

    @PostMapping("/barang/ubah")
    public String updateBuku(@ModelAttribute UpdateBarangRequestDTO barangDTO, Model model) {

        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        barangFromDTO.setTipeBarang(getTipeBarangFromSku(barangDTO.getSku())); // Handle tipeBarang null

        // Memanggil Service updateBuku
        barangService.updateBarang(barangFromDTO);

        // Add variabel untuk dirender di thymeleaf
        model.addAttribute("message", "Barang berhasil diubah");
        model.addAttribute("page", "barang");

        return "success";
    }

    @RequestMapping("/barang/{id}/hapus")
    public String hapusBarang(@PathVariable("id") String id, Model model) {
        var barang = barangService.getBarangBySku(id);
        barangService.deleteBarang(barang);

        // Add variabel untuk dirender di thymeleaf
        model.addAttribute("message", "Barang berhasil dihapus");
        model.addAttribute("page", "barang");
        return "success";
    }

    private Integer getTipeBarangFromSku(String sku) {
        // Extract the type prefix from the SKU
        String typePrefix = sku.substring(0, 4);

        // Map the type prefix back to the corresponding integer value
        Integer tipeBarang;
        switch (typePrefix) {
            case "ELEC":
                tipeBarang = 1;
                break;
            case "CLOT":
                tipeBarang = 2;
                break;
            case "FOOD":
                tipeBarang = 3;
                break;
            case "COSM":
                tipeBarang = 4;
                break;
            case "TOOL":
                tipeBarang = 5;
                break;
            default:
                throw new IllegalArgumentException("Invalid SKU format");
        }

        return tipeBarang;
    }

    private Integer countTotalStok(Barang barang) {
        Integer totalStok = 0;
        for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
            totalStok += gudangBarang.getStok();
        }
        return totalStok;
    }

    private String getStringTipeBarang(Barang barang) {
        String tipeBarang;
        if (barang.getTipeBarang().equals(1)) {
            tipeBarang = "Produk Elektronik";
        } else if (barang.getTipeBarang().equals(2)) {
            tipeBarang = "Pakaian & Aksesoris";
        } else if (barang.getTipeBarang().equals(3)) {
            tipeBarang = "Makanan & Minuman";
        } else if (barang.getTipeBarang().equals(4)) {
            tipeBarang = "Kosmetik";
        } else {
            tipeBarang = "Perlengkapan Rumah";
        }
        return tipeBarang;
    }

    private Integer getIntegerTipeBarang(CreateBarangRequestDTO createBarangRequestDTO) {
        Integer tipeBarang;
        if (createBarangRequestDTO.getTipeBarang().equals("Produk Elektronik")) {
            tipeBarang = 1;
        } else if (createBarangRequestDTO.getTipeBarang().equals("Pakaian & Aksesoris")) {
            tipeBarang = 2;
        } else if (createBarangRequestDTO.getTipeBarang().equals("Makanan & Minuman")) {
            tipeBarang = 3;
        } else if (createBarangRequestDTO.getTipeBarang().equals("Kosmetik")) {
            tipeBarang = 4;
        } else {
            tipeBarang = 5;
        }
        return tipeBarang;
    }
}
