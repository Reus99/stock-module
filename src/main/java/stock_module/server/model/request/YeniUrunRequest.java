package stock_module.server.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record YeniUrunRequest(@NotBlank String urunAd, @NotNull Long kategoriId, @NotBlank String birim, Integer kritikEsik, Integer baslangicMiktari) {}