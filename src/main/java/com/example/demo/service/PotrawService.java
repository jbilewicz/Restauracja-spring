package com.example.demo.service;

import com.example.demo.entity.Potrawa;
import com.example.demo.repository.PotrawRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PotrawService {

    private final PotrawRepository potrawRepository;

    public PotrawService(PotrawRepository potrawRepository) {
        this.potrawRepository = potrawRepository;
    }

    public List<Potrawa> findAll() { return potrawRepository.findAll(); }
    public Optional<Potrawa> findById(Long id) { return potrawRepository.findById(id); }
    public Potrawa save(Potrawa potrawa) { return potrawRepository.save(potrawa); }
    public void deleteById(Long id) { potrawRepository.deleteById(id); }
}