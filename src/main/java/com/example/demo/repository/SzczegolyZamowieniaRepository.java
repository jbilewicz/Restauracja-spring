package com.example.demo.repository;

import com.example.demo.dto.BestsellerDTO;
import com.example.demo.entity.SzczegolyZamowienia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SzczegolyZamowieniaRepository extends JpaRepository<SzczegolyZamowienia, Long> {

    @Query("SELECT new com.example.demo.dto.BestsellerDTO(sz.potrawa.id, sz.potrawa.nazwa, SUM(sz.ilosc)) " +
           "FROM SzczegolyZamowienia sz " +
           "GROUP BY sz.potrawa.id, sz.potrawa.nazwa " +
           "ORDER BY SUM(sz.ilosc) DESC")
    List<BestsellerDTO> findBestsellery();
}