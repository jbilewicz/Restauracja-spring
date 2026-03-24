package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "klient")
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;
    private String nazwisko;

    @OneToMany(mappedBy = "klient", cascade = CascadeType.ALL)
    private List<Zamowienie> zamowienia;

    public Klient() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }

    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    public List<Zamowienie> getZamowienia() { return zamowienia; }
    public void setZamowienia(List<Zamowienie> zamowienia) { this.zamowienia = zamowienia; }
}