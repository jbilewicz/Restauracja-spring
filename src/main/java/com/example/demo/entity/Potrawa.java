package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "potrawa")
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

    public Potrawa() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public BigDecimal getCenaBazowa() { return cenaBazowa; }
    public void setCenaBazowa(BigDecimal cenaBazowa) { this.cenaBazowa = cenaBazowa; }

    public List<Receptury> getReceptury() { return receptury; }
    public void setReceptury(List<Receptury> receptury) { this.receptury = receptury; }

    public List<SzczegolyZamowienia> getSzczegoly() { return szczegoly; }
    public void setSzczegoly(List<SzczegolyZamowienia> szczegoly) { this.szczegoly = szczegoly; }
}