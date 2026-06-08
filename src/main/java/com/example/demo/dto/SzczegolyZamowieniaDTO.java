package com.example.demo.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.controller.SzczegolyZamowieniaController;
import com.example.demo.entity.SzczegolyZamowienia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SzczegolyZamowieniaDTO extends RepresentationModel<SzczegolyZamowieniaDTO> {
    private Long id;
    
    private Long zamowienieId;
    private Long potrawaId;
    private String potrawaNazwa;
    
    private Integer ilosc;
    private BigDecimal cenaJednostkowa;

    public SzczegolyZamowieniaDTO(SzczegolyZamowienia szczegoly) {
        this.id = szczegoly.getId();
        this.ilosc = szczegoly.getIlosc();
        this.cenaJednostkowa = szczegoly.getCenaJednostkowa();
        
        if (szczegoly.getZamowienie() != null) {
            this.zamowienieId = szczegoly.getZamowienie().getId();
        }
        
        if (szczegoly.getPotrawa() != null) {
            this.potrawaId = szczegoly.getPotrawa().getId();
            this.potrawaNazwa = szczegoly.getPotrawa().getNazwa();
        }

        // Link do samej pozycji
        this.add(linkTo(methodOn(SzczegolyZamowieniaController.class).getSzczegolyById(this.id)).withSelfRel());
        
        // Link wyciągający całą listę dla danego zamówienia
        if (this.zamowienieId != null) {
            this.add(linkTo(methodOn(SzczegolyZamowieniaController.class).getSzczegolyByZamowienie(this.zamowienieId)).withRel("wszystkie_pozycje_zamowienia"));
        }
    }
}