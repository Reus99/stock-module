package stock_module.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stock_module.server.enums.IslemTipi;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class StokLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long urunId;
    private String urunAd;
    private Integer islemMiktari;
    private Integer sonStok;
    
    @Enumerated(EnumType.STRING)
    private IslemTipi islemTipi;
    
    private String personelAd;
    private String aciklama;
    private LocalDateTime islemTarihi = LocalDateTime.now();
}