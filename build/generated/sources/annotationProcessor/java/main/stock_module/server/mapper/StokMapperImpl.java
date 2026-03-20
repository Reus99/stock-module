package stock_module.server.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import stock_module.server.entity.Kategori;
import stock_module.server.entity.StokItem;
import stock_module.server.entity.StokLog;
import stock_module.server.enums.Durum;
import stock_module.server.enums.IslemTipi;
import stock_module.server.model.request.YeniUrunRequest;
import stock_module.server.model.response.KategoriResponse;
import stock_module.server.model.response.LogResponse;
import stock_module.server.model.response.StokResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-20T15:15:47+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.0.jar, environment: Java 17.0.18 (Amazon.com Inc.)"
)
@Component
public class StokMapperImpl implements StokMapper {

    @Override
    public KategoriResponse toKategoriResponse(Kategori kategori) {
        if ( kategori == null ) {
            return null;
        }

        Long id = null;
        String ad = null;
        String aciklama = null;
        Durum durum = null;

        id = kategori.getId();
        ad = kategori.getAd();
        aciklama = kategori.getAciklama();
        durum = kategori.getDurum();

        KategoriResponse kategoriResponse = new KategoriResponse( id, ad, aciklama, durum );

        return kategoriResponse;
    }

    @Override
    public StokResponse toStokResponse(StokItem item) {
        if ( item == null ) {
            return null;
        }

        String kategoriAd = null;
        Long id = null;
        String urunAd = null;
        Integer miktar = null;
        String birim = null;
        Durum durum = null;

        kategoriAd = itemKategoriAd( item );
        id = item.getId();
        urunAd = item.getUrunAd();
        miktar = item.getMiktar();
        birim = item.getBirim();
        durum = item.getDurum();

        boolean kritikDurum = item.getMiktar() <= item.getKritikEsik();

        StokResponse stokResponse = new StokResponse( id, urunAd, kategoriAd, miktar, birim, kritikDurum, durum );

        return stokResponse;
    }

    @Override
    public StokItem toEntity(YeniUrunRequest request) {
        if ( request == null ) {
            return null;
        }

        StokItem stokItem = new StokItem();

        if ( request.baslangicMiktari() != null ) {
            stokItem.setMiktar( request.baslangicMiktari() );
        }
        else {
            stokItem.setMiktar( 0 );
        }
        stokItem.setUrunAd( request.urunAd() );
        stokItem.setBirim( request.birim() );
        stokItem.setKritikEsik( request.kritikEsik() );

        stokItem.setDurum( Durum.AKTIF );

        return stokItem;
    }

    @Override
    public LogResponse toLogResponse(StokLog log) {
        if ( log == null ) {
            return null;
        }

        Long id = null;
        Long urunId = null;
        String urunAd = null;
        Integer islemMiktari = null;
        Integer sonStok = null;
        IslemTipi islemTipi = null;
        String personelAd = null;
        String aciklama = null;
        LocalDateTime islemTarihi = null;

        id = log.getId();
        urunId = log.getUrunId();
        urunAd = log.getUrunAd();
        islemMiktari = log.getIslemMiktari();
        sonStok = log.getSonStok();
        islemTipi = log.getIslemTipi();
        personelAd = log.getPersonelAd();
        aciklama = log.getAciklama();
        islemTarihi = log.getIslemTarihi();

        LogResponse logResponse = new LogResponse( id, urunId, urunAd, islemMiktari, sonStok, islemTipi, personelAd, aciklama, islemTarihi );

        return logResponse;
    }

    @Override
    public List<LogResponse> toLogResponseList(List<StokLog> logs) {
        if ( logs == null ) {
            return null;
        }

        List<LogResponse> list = new ArrayList<LogResponse>( logs.size() );
        for ( StokLog stokLog : logs ) {
            list.add( toLogResponse( stokLog ) );
        }

        return list;
    }

    @Override
    public List<StokResponse> toStokResponseList(List<StokItem> items) {
        if ( items == null ) {
            return null;
        }

        List<StokResponse> list = new ArrayList<StokResponse>( items.size() );
        for ( StokItem stokItem : items ) {
            list.add( toStokResponse( stokItem ) );
        }

        return list;
    }

    private String itemKategoriAd(StokItem stokItem) {
        if ( stokItem == null ) {
            return null;
        }
        Kategori kategori = stokItem.getKategori();
        if ( kategori == null ) {
            return null;
        }
        String ad = kategori.getAd();
        if ( ad == null ) {
            return null;
        }
        return ad;
    }
}
