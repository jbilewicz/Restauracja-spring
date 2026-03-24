package com.example.demo.repository;

import com.example.demo.entity.SzczegolyZamowienia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SzczegolyZamowieniaRepository extends JpaRepository<SzczegolyZamowienia, Long> {
}