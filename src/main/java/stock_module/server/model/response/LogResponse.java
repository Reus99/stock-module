package stock_module.server.model.response;

import stock_module.server.enums.IslemTipi;

import java.time.LocalDateTime;

public record LogResponse(Long id, Long urunId, String urunAd, Integer islemMiktari, Integer sonStok, IslemTipi islemTipi, String personelAd, String aciklama, LocalDateTime islemTarihi) {}