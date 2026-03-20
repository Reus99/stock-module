package stock_module.server.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StokHareketRequest(@NotNull Long urunId, @Min(1) Integer miktar, String aciklama,
                                 @NotBlank String personelAd) {
}