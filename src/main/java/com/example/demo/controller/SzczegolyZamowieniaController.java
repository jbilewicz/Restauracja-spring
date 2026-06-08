package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.SzczegolyZamowieniaDTO;
import com.example.demo.entity.SzczegolyZamowienia;
import com.example.demo.service.SzczegolyZamowieniaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/szczegoly")
@RequiredArgsConstructor
public class SzczegolyZamowieniaController {

    private final SzczegolyZamowieniaService szczegolyService;

    @GetMapping
    public CollectionModel<SzczegolyZamowieniaDTO> getAllSzczegoly() {
        List<SzczegolyZamowieniaDTO> szczegoly = szczegolyService.getAllSzczegoly();
        return CollectionModel.of(szczegoly, linkTo(methodOn(SzczegolyZamowieniaController.class).getAllSzczegoly()).withSelfRel());
    }

    @GetMapping("/{id}")
    public SzczegolyZamowieniaDTO getSzczegolyById(@PathVariable("id") Long id) {
        return szczegolyService.getSzczegolyById(id);
    }

    @GetMapping("/zamowienie/{zamowienieId}")
    public CollectionModel<SzczegolyZamowieniaDTO> getSzczegolyByZamowienie(@PathVariable("zamowienieId") Long zamowienieId) {
        List<SzczegolyZamowieniaDTO> szczegoly = szczegolyService.getSzczegolyByZamowienieId(zamowienieId);
        return CollectionModel.of(szczegoly, linkTo(methodOn(SzczegolyZamowieniaController.class).getSzczegolyByZamowienie(zamowienieId)).withSelfRel());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SzczegolyZamowieniaDTO addSzczegoly(@RequestBody SzczegolyZamowienia szczegol) {
        return szczegolyService.createSzczegoly(szczegol);
    }

    @PutMapping("/{id}/ilosc")
    public SzczegolyZamowieniaDTO updateIlosc(@PathVariable("id") Long id, @RequestParam("nowaIlosc") Integer nowaIlosc) {
        return szczegolyService.updateIlosc(id, nowaIlosc);
    }

    @DeleteMapping("/{id}")
    public String deleteSzczegoly(@PathVariable("id") Long id) {
        szczegolyService.deleteSzczegoly(id);
        return "Usunięto pozycję o ID: " + id;
    }
}