package com.example.demo.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.controller.PotrawaController;
import com.example.demo.entity.Potrawa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PotrawaDTO extends RepresentationModel<PotrawaDTO> {
    private Long id;
    private String nazwa;
    private BigDecimal cenaBazowa;
    
    public PotrawaDTO(Potrawa potrawa) {
        this.id = potrawa.getId();
        this.nazwa = potrawa.getNazwa();
        this.cenaBazowa = potrawa.getCenaBazowa();

        this.add(linkTo(methodOn(PotrawaController.class).getPotrawaById(this.id)).withSelfRel());
    }
}