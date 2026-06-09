package com.example.demo.dto;

import com.example.demo.entity.Skladnik;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RaportBrakowDTO {
    private Long skladnikId;
    private String nazwaSkladnika;
    private Integer stanMagazynowy;
    private List<String> zagrozonePotrawy; 

    public RaportBrakowDTO(Skladnik skladnik) {
        this.skladnikId = skladnik.getId();
        this.nazwaSkladnika = skladnik.getNazwa();
        this.stanMagazynowy = skladnik.getStanMagazynowy();

        if (skladnik.getReceptury() != null) {
            this.zagrozonePotrawy = skladnik.getReceptury().stream()
                    .filter(r -> r.getPotrawa() != null)
                    .map(r -> r.getPotrawa().getNazwa())
                    .distinct()
                    .collect(Collectors.toList());
        }
    }
}