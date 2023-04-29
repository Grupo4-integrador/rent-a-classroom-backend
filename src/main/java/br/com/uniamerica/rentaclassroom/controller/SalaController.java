package br.com.uniamerica.rentaclassroom.controller;

import br.com.uniamerica.rentaclassroom.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/sala")
public class SalaController {
    @Autowired
    private SalaRepository salaRepository;

    //@GetMapping("/{id}")
    //@GetMapping
    //@GetMapping
    //@PutMapping
    //@PostMapping
    //@DeleteMapping
}
