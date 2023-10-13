package apap.ti.silogistik2106705335.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman
                .getListPermintaanPengirimanBarang()) {
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
        }
        permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);

        String message = "Permintaan Pengiriman berhasil dibuat";
        model.addAttribute("page", "permintaan-pengiriman");
        model.addAttribute("message", message);
        return "success";
    }

    @GetMapping(value = "/permintaan-pengiriman/delete/{id}")
    public String cancelPermintaanPengiriman(@PathVariable("id") Long id, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);

        // Calculate the time difference between current time and waktuPermintaan
        Date currentTime = new Date();
        long timeDifferenceInMilliseconds = currentTime.getTime() - permintaanPengiriman.getWaktuPermintaan().getTime();

        // Convert milliseconds to hours
        long hoursDifference = timeDifferenceInMilliseconds / (60 * 60 * 1000);

        // Check if the request can be canceled (within 24 hours)
        if (hoursDifference <= 24) {
            permintaanPengirimanService.cancelPermintaanPengiriman(permintaanPengiriman);
            String message = "Permintaan pengiriman dengan nomor pengiriman "
                    + permintaanPengiriman.getNomorPengiriman() + " berhasil dihapus";
            model.addAttribute("message", message);
            return "success-cancel";
        } else {
            String message = "Permintaan pengiriman dengan nomor pengiriman "
                    + permintaanPengiriman.getNomorPengiriman()
                    + " tidak berhasil dihapus karena permintaan dibuat lebih dari 24 jam terakhir";
            model.addAttribute("message", message);
            return "error-cancel";
        }
    }

    // BONUS
    @GetMapping(value = "/filter-permintaan-pengiriman")
    public String filterPermintaanPengiriman(
            @RequestParam(name = "sku", required = false) String sku,
            @RequestParam(name = "start-date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "end-date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            Model model) {

        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        // Filter by SKU
        List<PermintaanPengiriman> filteredPermintaanPengirimanBySku = new ArrayList<>();
        if (sku != null && !sku.isEmpty()) {
            for (PermintaanPengiriman permintaanPengiriman : listPermintaanPengiriman) {
                for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman
                        .getListPermintaanPengirimanBarang()) {
                    if (permintaanPengirimanBarang.getBarang().getSku().equals(sku)) {
                        filteredPermintaanPengirimanBySku.add(permintaanPengiriman);
                        // TODO: get out of this for loop for (PermintaanPengirimanBarang
                        // permintaanPengirimanBarang :
                        // permintaanPengiriman.getListPermintaanPengirimanBarang())
                    }
                }
            }
        }

        // Filter by date range
        List<PermintaanPengiriman> filteredPermintaanPengirimanByDate = new ArrayList<>();
        // Check if neither startDate nor endDate is provided
        if (startDate == null && endDate == null) {
            // If neither date is provided, there's no need to iterate through the list.
            // You can return an empty or original list here, or handle it as needed.
        } else {
            for (PermintaanPengiriman permintaanPengiriman : listPermintaanPengiriman) {
                Date permintaanDate = permintaanPengiriman.getWaktuPermintaan();
                LocalDate localPermintaanDate = permintaanDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                // If both startDate and endDate are not null, filter by date range
                if (startDate != null && endDate != null) {
                    if (localPermintaanDate.isEqual(startDate)
                            || (localPermintaanDate.isAfter(startDate) && localPermintaanDate.isBefore(endDate))) {
                        filteredPermintaanPengirimanByDate.add(permintaanPengiriman);
                    }
                }
                // If only startDate is provided, filter by starting from startDate
                else if (startDate != null) {
                    if (localPermintaanDate.isEqual(startDate) || localPermintaanDate.isAfter(startDate)) {
                        filteredPermintaanPengirimanByDate.add(permintaanPengiriman);
                    }
                }
                // If only endDate is provided, filter by ending before endDate
                else if (endDate != null) {
                    if (localPermintaanDate.isEqual(endDate) || localPermintaanDate.isBefore(endDate)) {
                        filteredPermintaanPengirimanByDate.add(permintaanPengiriman);
                    }
                }
            }
        }

        // If no filters applied, return the original list
        if (sku != null && !sku.isEmpty() && (startDate != null || endDate != null)) {
            List<PermintaanPengiriman> intersectionList = new ArrayList<>();

            for (PermintaanPengiriman skuItem : filteredPermintaanPengirimanBySku) {
                if (filteredPermintaanPengirimanByDate.contains(skuItem)) {
                    intersectionList.add(skuItem);
                }
            }

            listPermintaanPengiriman = intersectionList;
        } else if (sku != null && !sku.isEmpty()) {
            listPermintaanPengiriman = filteredPermintaanPengirimanBySku;
        } else if (startDate != null || endDate != null) {
            listPermintaanPengiriman = filteredPermintaanPengirimanByDate;
        }

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);

        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);

        return "permintaan-pengiriman-filter";
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
