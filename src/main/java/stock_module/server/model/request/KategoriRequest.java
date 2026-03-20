package stock_module.server.model.request;

import jakarta.validation.constraints.NotBlank;

public record KategoriRequest(@NotBlank String ad, String aciklama) {
}