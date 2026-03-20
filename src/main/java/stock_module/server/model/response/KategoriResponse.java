package stock_module.server.model.response;

import stock_module.server.enums.Durum;

public record KategoriResponse(Long id, String ad, String aciklama, Durum durum) {}