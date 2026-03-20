package stock_module.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stock_module.server.enums.IslemTipi;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class StokHareket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stok_item_id")
    private StokItem stokItem;

    private Integer miktar;

    @Enumerated(EnumType.STRING)
    private IslemTipi islemTipi;

    private String aciklama;
    private String personelAd;
    private LocalDateTime islemTarihi = LocalDateTime.now();
}