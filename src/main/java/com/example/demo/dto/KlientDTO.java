package com.example.demo.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo.controller.KlientController;
import com.example.demo.entity.Klient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KlientDTO extends RepresentationModel<KlientDTO> {
	private Long id;
	private String imie;
	private String nazwisko;
	
	public KlientDTO(Klient klient) {
		this.id = klient.getId();
		this.imie = klient.getImie();
		this.nazwisko = klient.getNazwisko();
		
		this.add(linkTo(methodOn(KlientController.class).getKlientById(this.id)).withSelfRel());
		
		this.add(linkTo(methodOn(KlientController.class).getZamowieniaKlienta(this.id)).withRel("zamowienia"));
	}
}
