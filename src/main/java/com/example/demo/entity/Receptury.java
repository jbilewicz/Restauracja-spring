package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receptury")
@Getter 
@Setter
@NoArgsConstructor
public class Receptury {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "potrawa_id")
    private Potrawa potrawa;

    @ManyToOne
    @JoinColumn(name = "skladnik_id")
    private Skladnik skladnik;

    private Float iloscSkladnika;
    private String jednostka;
}