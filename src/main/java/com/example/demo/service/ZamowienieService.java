package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ZamowienieDTO;
import com.example.demo.entity.Klient;
import com.example.demo.entity.Skladnik;
import com.example.demo.entity.Zamowienie;
import com.example.demo.repository.KlientRepository;
import com.example.demo.repository.SkladnikRepository;
import com.example.demo.repository.ZamowienieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZamowienieService {

    private final ZamowienieRepository zamowienieRepository;
    private final KlientRepository klientRepository;
    private final SkladnikRepository skladnikRepository;

    public List<ZamowienieDTO> getAllZamowienia() {
        return zamowienieRepository.findAll().stream()
                .map(ZamowienieDTO::new)
                .collect(Collectors.toList());
    }

    public ZamowienieDTO getZamowienieById(Long id) {
        Zamowienie zamowienie = zamowienieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono zamówienia o ID: " + id));
        return new ZamowienieDTO(zamowienie);
    }

    public List<ZamowienieDTO> getZamowieniaByKlient(Long klientId) {
        return zamowienieRepository.findAll().stream()
                .filter(z -> z.getKlient() != null && z.getKlient().getId().equals(klientId))
                .map(ZamowienieDTO::new)
                .collect(Collectors.toList());
    }

    public List<ZamowienieDTO> getZamowieniaByStatus(String status) {
        return zamowienieRepository.findAll().stream()
                .filter(z -> status.equalsIgnoreCase(z.getStatus()))
                .map(ZamowienieDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ZamowienieDTO createZamowienie(Zamowienie zamowienie) {
        Klient klient = klientRepository.findById(zamowienie.getKlient().getId())
                .orElseThrow(() -> new RuntimeException("Brak klienta o podanym ID"));
        
        zamowienie.setKlient(klient);
        zamowienie.setData(LocalDateTime.now()); 
        zamowienie.setStatus("Złożone"); 
        
        return new ZamowienieDTO(zamowienieRepository.save(zamowienie));
    }

    @Transactional
    public ZamowienieDTO updateStatus(Long id, String nowyStatus) {
        Zamowienie zamowienie = zamowienieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono zamówienia o ID: " + id));

        String obecnyStatus = zamowienie.getStatus();

        if ("W przygotowaniu".equalsIgnoreCase(nowyStatus) && !"W przygotowaniu".equalsIgnoreCase(obecnyStatus)) {
            aktualizujStanyMagazynowe(zamowienie, -1);
        } 
        else if ("Anulowane".equalsIgnoreCase(nowyStatus) && "W przygotowaniu".equalsIgnoreCase(obecnyStatus)) {
            aktualizujStanyMagazynowe(zamowienie, 1);
        }

        zamowienie.setStatus(nowyStatus);
        return new ZamowienieDTO(zamowienieRepository.save(zamowienie));
    }

    @Transactional
    public void deleteZamowienie(Long id) {
        if (!zamowienieRepository.existsById(id)) {
            throw new RuntimeException("Zamówienie o podanym ID nie istnieje");
        }
        zamowienieRepository.deleteById(id);
    }

    private void aktualizujStanyMagazynowe(Zamowienie zamowienie, int mnoznik) {
        if (zamowienie.getSzczegoly() != null) {
            for (var szczegol : zamowienie.getSzczegoly()) {
                int iloscZamowionychPotraw = szczegol.getIlosc();
                
                if (szczegol.getPotrawa() != null && szczegol.getPotrawa().getReceptury() != null) {
                    for (var receptura : szczegol.getPotrawa().getReceptury()) {
                        Skladnik skladnik = receptura.getSkladnik();
                        if (skladnik != null) {
                            int zuzycie = Math.round(receptura.getIloscSkladnika() * iloscZamowionychPotraw);
                            skladnik.setStanMagazynowy(skladnik.getStanMagazynowy() + (zuzycie * mnoznik));
                            skladnikRepository.save(skladnik);
                        }
                    }
                }
            }
        }
    }
}