package stock_module.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock_module.server.entity.Kategori;
import stock_module.server.enums.Durum;

import java.util.List;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Long> {
    List<Kategori> findAllByDurum(Durum durum);
}