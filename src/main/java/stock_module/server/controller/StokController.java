package stock_module.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock_module.server.enums.Durum;
import stock_module.server.enums.IslemTipi;
import stock_module.server.mapper.StokMapper;
import stock_module.server.model.request.KategoriRequest;
import stock_module.server.model.request.StokHareketRequest;
import stock_module.server.model.request.YeniUrunRequest;
import stock_module.server.model.response.KategoriResponse;
import stock_module.server.model.response.LogResponse;
import stock_module.server.model.response.StokResponse;
import stock_module.server.repository.StokLogRepository;
import stock_module.server.service.StokService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stok") @RequiredArgsConstructor
@CrossOrigin("*")
public class StokController {
    private final StokService service;
    private final StokLogRepository logRepo;
    private final StokMapper mapper;

    @GetMapping("/liste")
    public List<StokResponse> liste() { return service.tumAktifStoklar(); }

    @PostMapping("/kategori")
    public ResponseEntity<KategoriResponse> kategoriEkle(@RequestBody KategoriRequest r) { return ResponseEntity.ok(service.kategoriEkle(r)); }

    @PostMapping("/urun-tanimla") 
    public ResponseEntity<StokResponse> urunTanimla(@RequestBody YeniUrunRequest r) { return ResponseEntity.ok(service.yeniUrunTanimla(r)); }

    @PostMapping("/alim") 
    public ResponseEntity<StokResponse> alim(@RequestBody StokHareketRequest r) { return ResponseEntity.ok(service.stokIslemi(r, IslemTipi.ALIM)); }

    @PostMapping("/sarf") 
    public ResponseEntity<StokResponse> sarf(@RequestBody StokHareketRequest r) { return ResponseEntity.ok(service.stokIslemi(r, IslemTipi.SARF)); }

    @PatchMapping("/urun/durum/{id}") 
    public void durumGuncelle(@PathVariable Long id, @RequestParam Durum durum, @RequestParam String personel) { service.urunDurumGuncelle(id, durum, personel); }

    @GetMapping("/denetim") 
    public List<LogResponse> denetimRaporu() { return mapper.toLogResponseList(logRepo.findAllByOrderByIslemTarihiDesc()); }
}