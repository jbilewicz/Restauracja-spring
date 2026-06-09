package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SkladnikDTO;
import com.example.demo.entity.Skladnik;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkladnikRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkladnikService {

    private final SkladnikRepository skladnikRepository;

    public List<SkladnikDTO> getAllSkladniki() {
        return skladnikRepository.findAll().stream()
                .map(SkladnikDTO::new)
                .collect(Collectors.toList());
    }

    public SkladnikDTO getSkladnikById(Long id) {
        Skladnik skladnik = skladnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono składnika o ID: " + id));
        return new SkladnikDTO(skladnik);
    }

    public SkladnikDTO createSkladnik(Skladnik skladnik) {
        Skladnik saved = skladnikRepository.save(skladnik);
        return new SkladnikDTO(saved);
    }

    public SkladnikDTO dodajDostawe(Long id, Integer iloscDostarczona) {
        Skladnik skladnik = skladnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono składnika o ID: " + id));

        skladnik.setStanMagazynowy(skladnik.getStanMagazynowy() + iloscDostarczona);
        
        Skladnik updated = skladnikRepository.save(skladnik);
        return new SkladnikDTO(updated);
    }

    public void deleteSkladnik(Long id) {
        if (!skladnikRepository.existsById(id)) {
            throw new ResourceNotFoundException("Składnik o podanym ID nie istnieje");
        }
        skladnikRepository.deleteById(id);
    }
}