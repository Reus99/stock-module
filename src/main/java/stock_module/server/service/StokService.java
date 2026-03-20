package stock_module.server.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock_module.server.entity.Kategori;
import stock_module.server.entity.StokItem;
import stock_module.server.entity.StokLog;
import stock_module.server.enums.Durum;
import stock_module.server.enums.IslemTipi;
import stock_module.server.mapper.StokMapper;
import stock_module.server.model.request.KategoriRequest;
import stock_module.server.model.request.StokHareketRequest;
import stock_module.server.model.request.YeniUrunRequest;
import stock_module.server.model.response.KategoriResponse;
import stock_module.server.model.response.StokResponse;
import stock_module.server.repository.KategoriRepository;
import stock_module.server.repository.StokItemRepository;
import stock_module.server.repository.StokLogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StokService {
    private final StokItemRepository stokRepo;
    private final KategoriRepository kategoriRepo;
    private final StokLogRepository logRepo;
    private final StokMapper mapper;

    public List<StokResponse> tumAktifStoklar() {
        return mapper.toStokResponseList(stokRepo.findAllByDurum(Durum.AKTIF));
    }

    public KategoriResponse kategoriEkle(KategoriRequest req) {
        Kategori k = new Kategori();
        k.setAd(req.ad());
        k.setAciklama(req.aciklama());
        return mapper.toKategoriResponse(kategoriRepo.save(k));
    }

    public StokResponse yeniUrunTanimla(YeniUrunRequest req) {
        Kategori kat = kategoriRepo.findById(req.kategoriId()).orElseThrow();
        StokItem item = mapper.toEntity(req);
        item.setKategori(kat);
        StokItem saved = stokRepo.save(item);
        logYaz(saved, item.getMiktar(), IslemTipi.TANIMLAMA, "Yeni Kayıt", "Yönetim");
        return mapper.toStokResponse(saved);
    }

    public StokResponse stokIslemi(StokHareketRequest req, IslemTipi tip) {
        StokItem item = stokRepo.findById(req.urunId()).filter(s -> s.getDurum() == Durum.AKTIF).orElseThrow();
        if (tip == IslemTipi.SARF && item.getMiktar() < req.miktar()) throw new RuntimeException("Yetersiz Stok!");
        
        item.setMiktar(tip == IslemTipi.ALIM ? item.getMiktar() + req.miktar() : item.getMiktar() - req.miktar());
        logYaz(item, req.miktar(), tip, req.aciklama(), req.personelAd());
        return mapper.toStokResponse(stokRepo.save(item));
    }

    public void urunDurumGuncelle(Long id, Durum yeniDurum, String personel) {
        StokItem item = stokRepo.findById(id).orElseThrow();
        item.setDurum(yeniDurum);
        logYaz(item, 0, IslemTipi.GUNCELLEME, "Durum Değişti: " + yeniDurum, personel);
        stokRepo.save(item);
    }

    private void logYaz(StokItem item, Integer miktar, IslemTipi tip, String aciklama, String personel) {
        StokLog log = new StokLog();
        log.setUrunId(item.getId()); log.setUrunAd(item.getUrunAd());
        log.setIslemMiktari(miktar); log.setSonStok(item.getMiktar());
        log.setIslemTipi(tip); log.setAciklama(aciklama); log.setPersonelAd(personel);
        logRepo.save(log);
    }
}