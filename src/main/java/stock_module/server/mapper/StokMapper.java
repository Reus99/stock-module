package stock_module.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import stock_module.server.entity.Kategori;
import stock_module.server.entity.StokItem;
import stock_module.server.entity.StokLog;
import stock_module.server.model.request.YeniUrunRequest;
import stock_module.server.model.response.KategoriResponse;
import stock_module.server.model.response.LogResponse;
import stock_module.server.model.response.StokResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StokMapper {
    KategoriResponse toKategoriResponse(Kategori kategori);
    
    @Mapping(target = "kategoriAd", source = "kategori.ad")
    @Mapping(target = "kritikDurum", expression = "java(item.getMiktar() <= item.getKritikEsik())")
    StokResponse toStokResponse(StokItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "durum", constant = "AKTIF")
    @Mapping(target = "miktar", source = "baslangicMiktari", defaultValue = "0")
    StokItem toEntity(YeniUrunRequest request);

    LogResponse toLogResponse(StokLog log);
    List<LogResponse> toLogResponseList(List<StokLog> logs);
    List<StokResponse> toStokResponseList(List<StokItem> items);
}