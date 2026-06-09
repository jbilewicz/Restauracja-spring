package com.example.demo.repository;

import com.example.demo.entity.Potrawa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PotrawaRepository extends JpaRepository<Potrawa, Long> {

    @Query("SELECT DISTINCT p FROM Potrawa p JOIN p.receptury r WHERE r.skladnik.id = :skladnikId")
    List<Potrawa> findPotrawyBySkladnikId(@Param("skladnikId") Long skladnikId);
}