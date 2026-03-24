package com.example.demo.service;

import com.example.demo.entity.SzczegolyZamowienia;
import com.example.demo.repository.SzczegolyZamowieniaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SzczegolyZamowieniaService {

    private final SzczegolyZamowieniaRepository szczegolyRepository;

    public SzczegolyZamowieniaService(SzczegolyZamowieniaRepository szczegolyRepository) {
        this.szczegolyRepository = szczegolyRepository;
    }

    public List<SzczegolyZamowienia> findAll() { return szczegolyRepository.findAll(); }
    public Optional<SzczegolyZamowienia> findById(Long id) { return szczegolyRepository.findById(id); }
    public SzczegolyZamowienia save(SzczegolyZamowienia s) { return szczegolyRepository.save(s); }
    public void deleteById(Long id) { szczegolyRepository.deleteById(id); }
}