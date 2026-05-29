package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KlientDTO;
import com.example.demo.entity.Klient;
import com.example.demo.service.KlientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/klienci")
@RequiredArgsConstructor
public class KlientController {
	
	private final KlientService klientService;
	
	@GetMapping 
	public CollectionModel<KlientDTO> getAllKlienci() {
		List<KlientDTO> klienci = klientService.getAllKlienci();
		return CollectionModel.of(klienci, linkTo(methodOn(KlientController.class).getAllKlienci()).withSelfRel());
	}
	
	@GetMapping("/{id}") 
	public KlientDTO getKlientById(@PathVariable Long id) {
		return klientService.getKlientById(id);
	}
	
	@PostMapping
	public KlientDTO addKlient(@PathVariable Klient klient) {
		return klientService.createKlient(klient);
	}
	
	@DeleteMapping("/{id}")
	public String deleteKlient(@PathVariable Long id) {
		klientService.deleteKlient(id);
		return "Usunięto klienta o ID: " + id;
	}
	
	@GetMapping("/{id}/zamowienia")
	public Object getZamowieniaKlienta(@PathVariable Long id) {
		return "Endpoint z zamówieniami dla klienta: " + id;
	}
}
