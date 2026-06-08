package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RecepturyDTO;
import com.example.demo.entity.Receptury;
import com.example.demo.service.RecepturyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/receptury")
@RequiredArgsConstructor
public class RecepturyController {

    private final RecepturyService recepturyService;

    @GetMapping
    public CollectionModel<RecepturyDTO> getAllReceptury() {
        List<RecepturyDTO> receptury = recepturyService.getAllReceptury();
        return CollectionModel.of(receptury, linkTo(methodOn(RecepturyController.class).getAllReceptury()).withSelfRel());
    }

    @GetMapping("/{id}")
    public RecepturyDTO getRecepturaById(@PathVariable("id") Long id) {
        return recepturyService.getRecepturaById(id);
    }

    @GetMapping("/potrawa/{potrawaId}")
    public CollectionModel<RecepturyDTO> getRecepturyByPotrawa(@PathVariable("potrawaId") Long potrawaId) {
        List<RecepturyDTO> receptury = recepturyService.getRecepturyByPotrawaId(potrawaId);
        return CollectionModel.of(receptury, linkTo(methodOn(RecepturyController.class).getRecepturyByPotrawa(potrawaId)).withSelfRel());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecepturyDTO addReceptura(@RequestBody Receptury receptura) {
        return recepturyService.createReceptura(receptura);
    }

    @PutMapping("/{id}")
    public RecepturyDTO updateReceptura(@PathVariable("id") Long id, @RequestBody Receptury receptura) {
        return recepturyService.updateReceptura(id, receptura);
    }

    @DeleteMapping("/{id}")
    public String deleteReceptura(@PathVariable("id") Long id) {
        recepturyService.deleteReceptura(id);
        return "Usunięto składnik z receptury o ID: " + id;
    }
}