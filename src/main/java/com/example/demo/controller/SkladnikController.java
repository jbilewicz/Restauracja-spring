package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.SkladnikDTO;
import com.example.demo.entity.Skladnik;
import com.example.demo.service.SkladnikService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/skladniki")
@RequiredArgsConstructor
public class SkladnikController {

    private final SkladnikService skladnikService;

    @GetMapping
    public CollectionModel<SkladnikDTO> getAllSkladniki() {
        List<SkladnikDTO> skladniki = skladnikService.getAllSkladniki();
        return CollectionModel.of(skladniki, linkTo(methodOn(SkladnikController.class).getAllSkladniki()).withSelfRel());
    }

    @GetMapping("/{id}")
    public SkladnikDTO getSkladnikById(@PathVariable("id") Long id) {
        SkladnikDTO skladnik = skladnikService.getSkladnikById(id);
        skladnik.add(linkTo(methodOn(SkladnikController.class).getAllSkladniki()).withRel("wszystkie_skladniki"));
        return skladnik;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkladnikDTO addSkladnik(@RequestBody Skladnik skladnik) {
        return skladnikService.createSkladnik(skladnik);
    }

    @PutMapping("/{id}")
    public SkladnikDTO updateSkladnik(@PathVariable("id") Long id, @RequestBody Skladnik skladnik) {
        return skladnikService.updateSkladnik(id, skladnik);
    }

    @DeleteMapping("/{id}")
    public String deleteSkladnik(@PathVariable("id") Long id) {
        skladnikService.deleteSkladnik(id);
        return "Usunięto składnik o ID: " + id;
    }
    
    @GetMapping("/raport-brakow")
    public List<com.example.demo.dto.RaportBrakowDTO> generujRaportBrakow(
            @RequestParam(value = "prog", defaultValue = "50") Integer prog) {
        return skladnikService.getRaportBrakow(prog);
    }
}