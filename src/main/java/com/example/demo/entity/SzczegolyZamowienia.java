package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "szczegoly_zamowienia")
@Getter 
@Setter
@NoArgsConstructor
public class SzczegolyZamowienia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zamowienie_id")
    private Zamowienie zamowienie;

    @ManyToOne
    @JoinColumn(name = "potrawa_id")
    private Potrawa potrawa;

    private Integer ilosc;
    private BigDecimal cenaJednostkowa;
}