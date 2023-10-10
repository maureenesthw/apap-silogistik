package apap.ti.silogistik2106705335.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106705335.model.GudangBarang;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
}
