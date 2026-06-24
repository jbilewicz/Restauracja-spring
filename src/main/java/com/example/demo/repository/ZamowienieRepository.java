package com.example.demo.repository;

import com.example.demo.entity.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {

    @Query("SELECT SUM(sz.ilosc * sz.cenaJednostkowa) FROM SzczegolyZamowienia sz " +
           "WHERE sz.zamowienie.klient.id = :klientId AND sz.zamowienie.status != 'Anulowane'")
    BigDecimal obliczLTVKlienta(@Param("klientId") Long klientId);
    
    List<Zamowienie> findAllByKlientId(Long klientId);
}