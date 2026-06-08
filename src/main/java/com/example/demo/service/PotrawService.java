package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PotrawaDTO;
import com.example.demo.entity.Potrawa;
import com.example.demo.repository.PotrawRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PotrawService {

    private final PotrawRepository potrawRepository;

    public List<PotrawaDTO> getAllPotrawy() { 
        return potrawRepository.findAll().stream()
                .map(PotrawaDTO::new)
                .collect(Collectors.toList()); 
    }
    
    public PotrawaDTO getPotrawaById(Long id) { 
        Potrawa potrawa = potrawRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono potrawy o ID: " + id));
        return new PotrawaDTO(potrawa);
    }
    
    public PotrawaDTO createPotrawa(Potrawa potrawa) {
        Potrawa savedPotrawa = potrawRepository.save(potrawa);
        return new PotrawaDTO(savedPotrawa);
    }
    
    public PotrawaDTO updatePotrawa(Long id, Potrawa updatedPotrawa) {
        Potrawa existingPotrawa = potrawRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono potrawy o ID: " + id));
        
        existingPotrawa.setNazwa(updatedPotrawa.getNazwa());
        existingPotrawa.setCenaBazowa(updatedPotrawa.getCenaBazowa());
        
        Potrawa savedPotrawa = potrawRepository.save(existingPotrawa);
        
        return new PotrawaDTO(savedPotrawa);
    }
    
    public void deletePotrawa(Long id) {
        if (!potrawRepository.existsById(id)) {
            throw new RuntimeException("Potrawa o podanym ID nie istnieje");
        }
        potrawRepository.deleteById(id);
    }
}