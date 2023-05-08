package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/turma")
public class TurmaController {
    @Autowired
    private TurmaRepository turmaRepository;

    //@GetMapping("/{id}")
    //@GetMapping
    //@GetMapping
    //@PutMapping
    //@PostMapping
    //@DeleteMapping
}
