package com.example.demo.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.controller.ZamowienieController;
import com.example.demo.entity.Zamowienie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZamowienieDTO extends RepresentationModel<ZamowienieDTO> {
    private Long id;
    
    private Long klientId;
    private String klientImieNazwisko;
    
    private LocalDateTime data;
    private String status;

    public ZamowienieDTO(Zamowienie zamowienie) {
        this.id = zamowienie.getId();
        this.data = zamowienie.getData();
        this.status = zamowienie.getStatus();
        
        if (zamowienie.getKlient() != null) {
            this.klientId = zamowienie.getKlient().getId();
            this.klientImieNazwisko = zamowienie.getKlient().getImie() + " " + zamowienie.getKlient().getNazwisko();
        }

        // Link do samego siebie
        this.add(linkTo(methodOn(ZamowienieController.class).getZamowienieById(this.id)).withSelfRel());
    }
}