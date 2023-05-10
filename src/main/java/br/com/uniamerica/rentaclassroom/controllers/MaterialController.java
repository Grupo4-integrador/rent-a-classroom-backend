package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.repositories.MaterialRepository;
import br.com.uniamerica.rentaclassroom.entitys.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/material")
public class MaterialController {
    private MaterialRepository materialRepository;

    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Material material = this.materialRepository.findById(id).orElse(null);
        return material == null ?
                ResponseEntity.badRequest().body("Material não encontrado") : ResponseEntity.ok(material);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.materialRepository.findAll());
    }
    @GetMapping("/ativos")
    public ResponseEntity<?> listaAtivos() {
        return ResponseEntity.ok(this.materialRepository.findByAtivo(true));
    }
    //@PutMapping
    //@PostMapping
    //@DeleteMapping    Publica ou privada
}
