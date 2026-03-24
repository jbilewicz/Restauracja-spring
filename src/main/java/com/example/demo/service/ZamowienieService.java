package com.example.demo.service;

import com.example.demo.entity.Zamowienie;
import com.example.demo.repository.ZamowienieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZamowienieService {

    private final ZamowienieRepository zamowienieRepository;

    public ZamowienieService(ZamowienieRepository zamowienieRepository) {
        this.zamowienieRepository = zamowienieRepository;
    }

    public List<Zamowienie> findAll() { return zamowienieRepository.findAll(); }
    public Optional<Zamowienie> findById(Long id) { return zamowienieRepository.findById(id); }
    public Zamowienie save(Zamowienie zamowienie) { return zamowienieRepository.save(zamowienie); }
    public void deleteById(Long id) { zamowienieRepository.deleteById(id); }
}