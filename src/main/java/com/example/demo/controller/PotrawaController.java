package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PotrawaDTO;
import com.example.demo.entity.Potrawa;
import com.example.demo.service.PotrawService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/potrawy")
@RequiredArgsConstructor
public class PotrawaController {
    
    private final PotrawService potrawService;
    
    @GetMapping 
    public CollectionModel<PotrawaDTO> getAllPotrawy() {
        List<PotrawaDTO> potrawy = potrawService.getAllPotrawy();
        return CollectionModel.of(potrawy, linkTo(methodOn(PotrawaController.class).getAllPotrawy()).withSelfRel());
    }
    
    @GetMapping("/{id}") 
    public PotrawaDTO getPotrawaById(@PathVariable("id") Long id) {
        return potrawService.getPotrawaById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PotrawaDTO addPotrawa(@RequestBody Potrawa potrawa) {
        return potrawService.createPotrawa(potrawa);
    }
    
    @PutMapping("/{id}")
    public PotrawaDTO updatePotrawa(@PathVariable("id") Long id, @RequestBody Potrawa potrawa) {
        return potrawService.updatePotrawa(id, potrawa);
    }
    
    @DeleteMapping("/{id}")
    public String deletePotrawa(@PathVariable("id") Long id) {
        potrawService.deletePotrawa(id);
        return "Usunięto potrawę o ID: " + id;
    }
}