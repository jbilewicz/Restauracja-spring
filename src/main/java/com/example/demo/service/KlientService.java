package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.KlientDTO;
import com.example.demo.entity.Klient;
import com.example.demo.repository.KlientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KlientService {

    private final KlientRepository klientRepository;

    public List<KlientDTO> getAllKlienci() { 
    	return klientRepository.findAll().stream()
    			.map(KlientDTO::new)
    			.collect(Collectors.toList()); 
    }
    
    public KlientDTO getKlientById(Long id) { 
    	Klient klient = klientRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("Nie znaleziono klienta o ID: " + id));
    	return new KlientDTO(klient);
    }
    
    public KlientDTO createKlient(Klient klient) {
        Klient savedKlient = klientRepository.save(klient);
        return new KlientDTO(savedKlient);
    }
    
    public void deleteKlient(Long id) {
        if (!klientRepository.existsById(id)) {
            throw new RuntimeException("Klient o podanym ID nie istnieje");
        }
        klientRepository.deleteById(id);
    }
}