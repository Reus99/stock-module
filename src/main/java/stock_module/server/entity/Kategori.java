package stock_module.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stock_module.server.enums.Durum;


@Entity @Getter @Setter
public class Kategori {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ad;
    private String aciklama;

    @Enumerated(EnumType.STRING)
    private Durum durum = Durum.AKTIF;
}
