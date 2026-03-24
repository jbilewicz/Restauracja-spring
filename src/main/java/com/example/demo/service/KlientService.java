package com.example.demo.service;

import com.example.demo.entity.Klient;
import com.example.demo.repository.KlientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlientService {

    private final KlientRepository klientRepository;

    public KlientService(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    public List<Klient> findAll() { return klientRepository.findAll(); }
    public Optional<Klient> findById(Long id) { return klientRepository.findById(id); }
    public Klient save(Klient klient) { return klientRepository.save(klient); }
    public void deleteById(Long id) { klientRepository.deleteById(id); }
}