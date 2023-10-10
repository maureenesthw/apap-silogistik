package apap.ti.silogistik2106705335.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106705335.model.Karyawan;

@Repository
public interface KaryawanDb extends JpaRepository<Karyawan, Long> {
}
