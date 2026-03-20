package stock_module.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stock_module.server.enums.Durum;


@Entity @Getter @Setter
public class StokItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urunAd;
    private Integer miktar = 0;
    private String birim;
    private Integer kritikEsik = 5;

    @Enumerated(EnumType.STRING)
    private Durum durum = Durum.AKTIF;

    @ManyToOne(fetch = FetchType.LAZY)
    private Kategori kategori;
}