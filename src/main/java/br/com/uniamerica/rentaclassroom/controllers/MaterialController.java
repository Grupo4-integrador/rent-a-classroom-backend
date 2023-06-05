package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.Sala;
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
    public ResponseEntity<?> updateMaterial(@RequestParam("id") final Long id, @RequestBody @Validated Material material) {
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

    @DeleteMapping
    public ResponseEntity <?> deletar(@RequestParam("id") final Long id){
        final Material materialBanco = this.materialRepository.findById(id).orElse(null);
        try{
            this.materialRepository.delete(materialBanco);
        }
        catch(RuntimeException e){
            if(materialBanco.isAtivo()) {
                materialBanco.setAtivo(false);
                this.materialRepository.save(materialBanco);
                return ResponseEntity.internalServerError().body("Erro no delete, flag desativada!");
            }
            return ResponseEntity.internalServerError().body("Erro no delete, a flag ja está desativada");
        }
        return ResponseEntity.ok("Registro deletado");
    }
}
