package br.com.uniamerica.rentaclassroom.controller;

import br.com.uniamerica.rentaclassroom.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/material")
public class MaterialController {
    @Autowired
    private MaterialRepository materialRepository;

    //@GetMapping("/{id}")
    //@GetMapping
    //@GetMapping
    //@PutMapping
    //@PostMapping
    //@DeleteMapping
}
