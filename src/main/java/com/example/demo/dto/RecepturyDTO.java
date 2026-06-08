package com.example.demo.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.controller.RecepturyController;
import com.example.demo.entity.Receptury;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecepturyDTO extends RepresentationModel<RecepturyDTO> {
    private Long id;

    private Long potrawaId;
    private String potrawaNazwa;
    
    private Long skladnikId;
    private String skladnikNazwa;
    
    private Float iloscSkladnika;
    private String jednostka;

    public RecepturyDTO(Receptury receptura) {
        this.id = receptura.getId();
        this.iloscSkladnika = receptura.getIloscSkladnika();
        this.jednostka = receptura.getJednostka();
        
        if (receptura.getPotrawa() != null) {
            this.potrawaId = receptura.getPotrawa().getId();
            this.potrawaNazwa = receptura.getPotrawa().getNazwa();
        }
        
        if (receptura.getSkladnik() != null) {
            this.skladnikId = receptura.getSkladnik().getId();
            this.skladnikNazwa = receptura.getSkladnik().getNazwa();
        }

        this.add(linkTo(methodOn(RecepturyController.class).getRecepturaById(this.id)).withSelfRel());
        if (this.potrawaId != null) {
            this.add(linkTo(methodOn(RecepturyController.class).getRecepturyByPotrawa(this.potrawaId)).withRel("cala_receptura_potrawy"));
        }
    }
}