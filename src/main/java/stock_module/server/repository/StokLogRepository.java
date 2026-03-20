package stock_module.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock_module.server.entity.StokLog;

import java.util.List;

@Repository
public interface StokLogRepository extends JpaRepository<StokLog, Long> {
    List<StokLog> findAllByOrderByIslemTarihiDesc();
    
    List<StokLog> findAllByUrunIdOrderByIslemTarihiDesc(Long urunId);
}