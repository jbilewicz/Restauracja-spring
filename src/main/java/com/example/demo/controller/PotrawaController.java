package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PotrawaDTO;
import com.example.demo.entity.Potrawa;
import com.example.demo.service.PotrawaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/potrawy")
@RequiredArgsConstructor
public class PotrawaController {
    
    private final PotrawaService potrawService;
    
    @GetMapping 
    public CollectionModel<PotrawaDTO> getAllPotrawy() {
        List<PotrawaDTO> potrawy = potrawService.getAllPotrawy();
        return CollectionModel.of(potrawy, linkTo(methodOn(PotrawaController.class).getAllPotrawy()).withSelfRel());
    }
    
    @GetMapping("/menu")
    public CollectionModel<PotrawaDTO> getPelneMenu() {
        List<PotrawaDTO> menu = potrawService.getPelneMenu();
        return CollectionModel.of(menu, linkTo(methodOn(PotrawaController.class).getPelneMenu()).withSelfRel());
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

    @PutMapping("/aktualizuj-ceny/{skladnikId}")
    public String updateCenyKaskadowo(@PathVariable("skladnikId") Long skladnikId, @RequestParam("zmiana") BigDecimal zmianaCeny) {
        potrawService.aktualizujCenyKaskadowo(skladnikId, zmianaCeny);
        return "Zaktualizowano ceny potraw dla składnika o ID: " + skladnikId;
    }
}