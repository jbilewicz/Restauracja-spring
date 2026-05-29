package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "zamowienie")
@Getter 
@Setter
@NoArgsConstructor
public class Zamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klient klient;

    private LocalDateTime data;
    private String status;

    @OneToMany(mappedBy = "zamowienie", cascade = CascadeType.ALL)
    private List<SzczegolyZamowienia> szczegoly;
}