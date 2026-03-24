package com.example.demo.repository;

import com.example.demo.entity.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {
}