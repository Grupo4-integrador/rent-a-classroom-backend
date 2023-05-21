package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.repositories.MaterialRepository;
import br.com.uniamerica.rentaclassroom.entitys.Material;
import br.com.uniamerica.rentaclassroom.services.MaterialService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<?> createMaterial(@RequestBody @Validated final Material material) {
        try {
            this.materialService.createMaterial(material);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception error) {
            return ResponseEntity.internalServerError().body("Error: " + error.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateMaterial(@RequestParam("id") final Long id, @RequestBody Material material) {
        try {
            this.materialService.updateMaterial(id, material);
            return ResponseEntity.ok("Registro editado com sucesso");
        }
        catch (DataIntegrityViolationException error) {
            return ResponseEntity.internalServerError().body("Error: " + error.getCause().getCause().getMessage());
        }
        catch (RuntimeException error) {
            return ResponseEntity.internalServerError().body("Error: " + error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable("id") final Long id) {
        this.materialService.deleteMaterial(id);
        return ResponseEntity.ok("Registro deletado com sucesso");
    }
}
