package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "zamowienie")
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

    public Zamowienie() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Klient getKlient() { return klient; }
    public void setKlient(Klient klient) { this.klient = klient; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<SzczegolyZamowienia> getSzczegoly() { return szczegoly; }
    public void setSzczegoly(List<SzczegolyZamowienia> szczegoly) { this.szczegoly = szczegoly; }
}