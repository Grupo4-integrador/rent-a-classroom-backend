package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.repositories.MaterialRepository;
import br.com.uniamerica.rentaclassroom.entitys.Material;
import br.com.uniamerica.rentaclassroom.services.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/material")
public class MaterialController {
    private MaterialRepository materialRepository;
    private MaterialService materialService;

    public MaterialController(MaterialRepository materialRepository, MaterialService materialService) {
        this.materialRepository = materialRepository;
        this.materialService = materialService;
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
    @GetMapping("/ativo")
    public ResponseEntity<?> listaAtivos() {
        return ResponseEntity.ok(this.materialRepository.findByAtivo(true));
    }

    @PostMapping
    public ResponseEntity<?> createMaterial(@RequestBody final Material material) {
        try {
            this.materialService.createMaterial(material);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception error) {
            return ResponseEntity.internalServerError().body("Error: " + error.getMessage());
        }
    }
    //@DeleteMapping    Publica ou privada
}
