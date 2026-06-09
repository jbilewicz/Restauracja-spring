package com.example.demo.repository;

import com.example.demo.entity.Skladnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkladnikRepository extends JpaRepository<Skladnik, Long> {

    @Query("SELECT DISTINCT s FROM Skladnik s LEFT JOIN FETCH s.receptury r LEFT JOIN FETCH r.potrawa WHERE s.stanMagazynowy < :prog")
    List<Skladnik> findKrytyczneBraki(@Param("prog") Integer prog);
}