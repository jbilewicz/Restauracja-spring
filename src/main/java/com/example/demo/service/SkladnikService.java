package com.example.demo.service;

import com.example.demo.entity.Skladnik;
import com.example.demo.repository.SkladnikRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkladnikService {

    private final SkladnikRepository skladnikRepository;

    public SkladnikService(SkladnikRepository skladnikRepository) {
        this.skladnikRepository = skladnikRepository;
    }

    public List<Skladnik> findAll() { return skladnikRepository.findAll(); }
    public Optional<Skladnik> findById(Long id) { return skladnikRepository.findById(id); }
    public Skladnik save(Skladnik skladnik) { return skladnikRepository.save(skladnik); }
    public void deleteById(Long id) { skladnikRepository.deleteById(id); }
}