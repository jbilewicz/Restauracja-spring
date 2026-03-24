package com.example.demo.service;

import com.example.demo.entity.Receptury;
import com.example.demo.repository.RecepturyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecepturyService {

    private final RecepturyRepository recepturyRepository;

    public RecepturyService(RecepturyRepository recepturyRepository) {
        this.recepturyRepository = recepturyRepository;
    }

    public List<Receptury> findAll() { return recepturyRepository.findAll(); }
    public Optional<Receptury> findById(Long id) { return recepturyRepository.findById(id); }
    public Receptury save(Receptury receptury) { return recepturyRepository.save(receptury); }
    public void deleteById(Long id) { recepturyRepository.deleteById(id); }
}