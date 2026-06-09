package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RecepturyDTO;
import com.example.demo.entity.Potrawa;
import com.example.demo.entity.Receptury;
import com.example.demo.entity.Skladnik;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PotrawaRepository;
import com.example.demo.repository.RecepturyRepository;
import com.example.demo.repository.SkladnikRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecepturyService {

    private final RecepturyRepository recepturyRepository;
    private final PotrawaRepository potrawRepository;
    private final SkladnikRepository skladnikRepository;

    public List<RecepturyDTO> getAllReceptury() {
        return recepturyRepository.findAll().stream()
                .map(RecepturyDTO::new)
                .collect(Collectors.toList());
    }

    public RecepturyDTO getRecepturaById(Long id) {
        Receptury receptura = recepturyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono receptury o ID: " + id));
        return new RecepturyDTO(receptura);
    }

    public List<RecepturyDTO> getRecepturyByPotrawaId(Long potrawaId) {
        return recepturyRepository.findAll().stream()
                .filter(r -> r.getPotrawa() != null && r.getPotrawa().getId().equals(potrawaId))
                .map(RecepturyDTO::new)
                .collect(Collectors.toList());
    }

    public RecepturyDTO createReceptura(Receptury receptura) {
        Potrawa potrawa = potrawRepository.findById(receptura.getPotrawa().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Brak potrawy o podanym ID"));
        Skladnik skladnik = skladnikRepository.findById(receptura.getSkladnik().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Brak składnika o podanym ID"));
        
        receptura.setPotrawa(potrawa);
        receptura.setSkladnik(skladnik);
        
        Receptury saved = recepturyRepository.save(receptura);
        return new RecepturyDTO(saved);
    }

    public RecepturyDTO updateReceptura(Long id, Receptury updatedData) {
        Receptury istniejaca = recepturyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono receptury o ID: " + id));

        istniejaca.setIloscSkladnika(updatedData.getIloscSkladnika());
        istniejaca.setJednostka(updatedData.getJednostka());

        return new RecepturyDTO(recepturyRepository.save(istniejaca));
    }

    public void deleteReceptura(Long id) {
        if (!recepturyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receptura o podanym ID nie istnieje");
        }
        recepturyRepository.deleteById(id);
    }
}