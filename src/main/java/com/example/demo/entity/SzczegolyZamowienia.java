package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "szczegoly_zamowienia")
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

    public SzczegolyZamowienia() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Zamowienie getZamowienie() { return zamowienie; }
    public void setZamowienie(Zamowienie zamowienie) { this.zamowienie = zamowienie; }

    public Potrawa getPotrawa() { return potrawa; }
    public void setPotrawa(Potrawa potrawa) { this.potrawa = potrawa; }

    public Integer getIlosc() { return ilosc; }
    public void setIlosc(Integer ilosc) { this.ilosc = ilosc; }

    public BigDecimal getCenaJednostkowa() { return cenaJednostkowa; }
    public void setCenaJednostkowa(BigDecimal cenaJednostkowa) { this.cenaJednostkowa = cenaJednostkowa; }
}