package com.example.demo.repository;

import com.example.demo.entity.Skladnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkladnikRepository extends JpaRepository<Skladnik, Long> {
}