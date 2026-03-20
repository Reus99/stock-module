package stock_module.server.model.response;

import stock_module.server.enums.Durum;

public record StokResponse(Long id, String urunAd, String kategoriAd, Integer miktar, String birim, boolean kritikDurum, Durum durum) {}