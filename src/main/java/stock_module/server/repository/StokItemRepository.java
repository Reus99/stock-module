package stock_module.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import stock_module.server.entity.StokItem;
import stock_module.server.enums.Durum;

import java.util.List;

@Repository
public interface StokItemRepository extends JpaRepository<StokItem, Long> {
    List<StokItem> findAllByDurum(Durum durum);

    @Query("SELECT s FROM StokItem s WHERE s.durum = 'AKTIF' AND s.miktar <= s.kritikEsik")
    List<StokItem> findKritikStoklar();
    
    List<StokItem> findAllByKategoriIdAndDurum(Long kategoriId, Durum durum);
}