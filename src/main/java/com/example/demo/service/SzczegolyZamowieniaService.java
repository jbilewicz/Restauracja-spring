package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BestsellerDTO;
import com.example.demo.dto.SzczegolyZamowieniaDTO;
import com.example.demo.entity.Potrawa;
import com.example.demo.entity.SzczegolyZamowienia;
import com.example.demo.entity.Zamowienie;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PotrawaRepository;
import com.example.demo.repository.SzczegolyZamowieniaRepository;
import com.example.demo.repository.ZamowienieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SzczegolyZamowieniaService {

    private final SzczegolyZamowieniaRepository szczegolyRepository;
    private final PotrawaRepository potrawRepository;
    private final ZamowienieRepository zamowienieRepository;

    public List<SzczegolyZamowieniaDTO> getAllSzczegoly() {
        return szczegolyRepository.findAll().stream()
                .map(SzczegolyZamowieniaDTO::new)
                .collect(Collectors.toList());
    }

    public SzczegolyZamowieniaDTO getSzczegolyById(Long id) {
        SzczegolyZamowienia szczegoly = szczegolyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono szczegółów o ID: " + id));
        return new SzczegolyZamowieniaDTO(szczegoly);
    }

    public List<SzczegolyZamowieniaDTO> getSzczegolyByZamowienieId(Long zamowienieId) {
        return szczegolyRepository.findAll().stream()
                .filter(sz -> sz.getZamowienie() != null && sz.getZamowienie().getId().equals(zamowienieId))
                .map(SzczegolyZamowieniaDTO::new)
                .collect(Collectors.toList());
    }

    public SzczegolyZamowieniaDTO createSzczegoly(SzczegolyZamowienia szczegol) {
        Zamowienie zamowienie = zamowienieRepository.findById(szczegol.getZamowienie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Brak zamówienia o podanym ID"));
        
        Potrawa potrawa = potrawRepository.findById(szczegol.getPotrawa().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Brak potrawy o podanym ID"));

        szczegol.setZamowienie(zamowienie);
        szczegol.setPotrawa(potrawa);
 
        if (szczegol.getCenaJednostkowa() == null) {
            szczegol.setCenaJednostkowa(potrawa.getCenaBazowa());
        }

        SzczegolyZamowienia saved = szczegolyRepository.save(szczegol);
        return new SzczegolyZamowieniaDTO(saved);
    }

    public SzczegolyZamowieniaDTO updateIlosc(Long id, Integer nowaIlosc) {
        SzczegolyZamowienia istniejace = szczegolyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono pozycji o ID: " + id));

        istniejace.setIlosc(nowaIlosc);
        
        SzczegolyZamowienia updated = szczegolyRepository.save(istniejace);
        return new SzczegolyZamowieniaDTO(updated);
    }

    public void deleteSzczegoly(Long id) {
        if (!szczegolyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pozycja o podanym ID nie istnieje");
        }
        szczegolyRepository.deleteById(id);
    }
    public List<BestsellerDTO> getRankingBestsellerow() {
        return szczegolyRepository.findBestsellery();
    }
}