package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.repository.ZamowienieRepository;

import org.springframework.stereotype.Service;

import com.example.demo.dto.KlientDTO;
import com.example.demo.entity.Klient;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KlientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KlientService {

    private final KlientRepository klientRepository;

    private final ZamowienieRepository zamowienieRepository;
    
    public List<KlientDTO> getAllKlienci() { 
    	return klientRepository.findAll().stream()
    			.map(KlientDTO::new)
    			.collect(Collectors.toList()); 
    }
    
    public KlientDTO getKlientById(Long id) { 
    	Klient klient = klientRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono klienta o ID: " + id));
    	return new KlientDTO(klient);
    }
    
    public KlientDTO createKlient(Klient klient) {
        Klient savedKlient = klientRepository.save(klient);
        return new KlientDTO(savedKlient);
    }
    
    public KlientDTO updateKlient(Long id, Klient updatedKlient) {
    	Klient existingKlient = klientRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono klienta o ID: " + id));
    	
    	existingKlient.setImie(updatedKlient.getImie());
    	existingKlient.setNazwisko(updatedKlient.getNazwisko());
    	
    	Klient savedKlient = klientRepository.save(existingKlient);
    	
    	return new KlientDTO(savedKlient);
    }
    
    public void deleteKlient(Long id) {
        if (!klientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Klient o podanym ID nie istnieje");
        }
        klientRepository.deleteById(id);
    }
    

    public java.util.Map<String, Object> getLTV(Long klientId) {
        if (!klientRepository.existsById(klientId)) {
            throw new com.example.demo.exception.ResourceNotFoundException("Nie znaleziono klienta o ID: " + klientId);
        }

        BigDecimal ltv = zamowienieRepository.obliczLTVKlienta(klientId);

        if (ltv == null) {
            ltv = BigDecimal.ZERO;
        }

        java.util.Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("klientId", klientId);
        response.put("totalLifetimeValue", ltv);
        response.put("waluta", "PLN");
        
        return response;
    }
}