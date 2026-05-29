package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "skladnik")
@Getter 
@Setter
@NoArgsConstructor
public class Skladnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private Integer stanMagazynowy;

    @OneToMany(mappedBy = "skladnik", cascade = CascadeType.ALL)
    private List<Receptury> receptury;
}