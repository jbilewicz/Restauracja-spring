package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "receptury")
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

    public Receptury() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Potrawa getPotrawa() { return potrawa; }
    public void setPotrawa(Potrawa potrawa) { this.potrawa = potrawa; }

    public Skladnik getSkladnik() { return skladnik; }
    public void setSkladnik(Skladnik skladnik) { this.skladnik = skladnik; }

    public Float getIloscSkladnika() { return iloscSkladnika; }
    public void setIloscSkladnika(Float iloscSkladnika) { this.iloscSkladnika = iloscSkladnika; }

    public String getJednostka() { return jednostka; }
    public void setJednostka(String jednostka) { this.jednostka = jednostka; }
}