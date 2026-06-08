package com.example.demo.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.controller.SkladnikController;
import com.example.demo.entity.Skladnik;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkladnikDTO extends RepresentationModel<SkladnikDTO> {
    private Long id;
    private String nazwa;
    private Integer stanMagazynowy;

    public SkladnikDTO(Skladnik skladnik) {
        this.id = skladnik.getId();
        this.nazwa = skladnik.getNazwa();
        this.stanMagazynowy = skladnik.getStanMagazynowy();
        
        // Link HATEOAS
        this.add(linkTo(methodOn(SkladnikController.class).getSkladnikById(this.id)).withSelfRel());
    }
}