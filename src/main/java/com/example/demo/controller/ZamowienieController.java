package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ZamowienieDTO;
import com.example.demo.entity.Zamowienie;
import com.example.demo.service.ZamowienieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/zamowienia")
@RequiredArgsConstructor
public class ZamowienieController {

    private final ZamowienieService zamowienieService;

    @GetMapping
    public CollectionModel<ZamowienieDTO> getAllZamowienia() {
        List<ZamowienieDTO> zamowienia = zamowienieService.getAllZamowienia();
        return CollectionModel.of(zamowienia, linkTo(methodOn(ZamowienieController.class).getAllZamowienia()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ZamowienieDTO getZamowienieById(@PathVariable("id") Long id) {
        return zamowienieService.getZamowienieById(id);
    }

    @GetMapping("/klient/{klientId}")
    public CollectionModel<ZamowienieDTO> getZamowieniaByKlient(@PathVariable("klientId") Long klientId) {
        List<ZamowienieDTO> zamowienia = zamowienieService.getZamowieniaByKlient(klientId);
        return CollectionModel.of(zamowienia, linkTo(methodOn(ZamowienieController.class).getZamowieniaByKlient(klientId)).withSelfRel());
    }

    @GetMapping("/status/{status}")
    public CollectionModel<ZamowienieDTO> getZamowieniaByStatus(@PathVariable("status") String status) {
        List<ZamowienieDTO> zamowienia = zamowienieService.getZamowieniaByStatus(status);
        return CollectionModel.of(zamowienia, linkTo(methodOn(ZamowienieController.class).getZamowieniaByStatus(status)).withSelfRel());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ZamowienieDTO addZamowienie(@RequestBody Zamowienie zamowienie) {
        return zamowienieService.createZamowienie(zamowienie);
    }

    @PutMapping("/{id}/status")
    public ZamowienieDTO updateStatus(@PathVariable("id") Long id, @RequestParam("nowyStatus") String nowyStatus) {
        return zamowienieService.updateStatus(id, nowyStatus);
    }

    @DeleteMapping("/{id}")
    public String deleteZamowienie(@PathVariable("id") Long id) {
        zamowienieService.deleteZamowienie(id);
        return "Anulowano/usunięto zamówienie o ID: " + id;
    }
}