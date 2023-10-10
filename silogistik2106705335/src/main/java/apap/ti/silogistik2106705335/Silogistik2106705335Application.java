package apap.ti.silogistik2106705335;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106705335.DTO.GudangMapper;
import apap.ti.silogistik2106705335.DTO.KaryawanMapper;
import apap.ti.silogistik2106705335.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106705335.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106705335.service.GudangService;
import apap.ti.silogistik2106705335.service.KaryawanService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106705335Application {

	// public static void main(String[] args) {
	// SpringApplication.run(Silogistik2106705335Application.class, args);
	// }
	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106705335Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService, GudangMapper gudangMapper,
			KaryawanMapper karyawanMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			var gudangDTO = new CreateGudangRequestDTO();
			gudangDTO.setAlamatGudang(faker.address().fullAddress());
			gudangDTO.setNama(faker.company().name());

			var karyawanDTO = new CreateKaryawanRequestDTO();
			karyawanDTO.setNama(faker.name().fullName());
			karyawanDTO.setJenisKelamin(faker.number().numberBetween(1, 3));
			karyawanDTO.setTanggalLahir(faker.date().birthday());

			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);

			gudangService.saveGudang(gudang);
			karyawanService.saveKaryawan(karyawan);

		};
	}

}
