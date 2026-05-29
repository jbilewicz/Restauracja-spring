package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "potrawa")
@Getter 
@Setter
@NoArgsConstructor
public class Potrawa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private BigDecimal cenaBazowa;

    @OneToMany(mappedBy = "potrawa", cascade = CascadeType.ALL)
    private List<Receptury> receptury;

    @OneToMany(mappedBy = "potrawa", cascade = CascadeType.ALL)
    private List<SzczegolyZamowienia> szczegoly;
}