package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "skladnik")
public class Skladnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private Integer stanMagazynowy;

    @OneToMany(mappedBy = "skladnik", cascade = CascadeType.ALL)
    private List<Receptury> receptury;

    public Skladnik() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public Integer getStanMagazynowy() { return stanMagazynowy; }
    public void setStanMagazynowy(Integer stanMagazynowy) { this.stanMagazynowy = stanMagazynowy; }

    public List<Receptury> getReceptury() { return receptury; }
    public void setReceptury(List<Receptury> receptury) { this.receptury = receptury; }
}