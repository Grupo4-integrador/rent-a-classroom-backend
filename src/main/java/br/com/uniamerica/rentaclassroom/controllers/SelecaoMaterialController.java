package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.SelecaoMaterial;
import br.com.uniamerica.rentaclassroom.repositories.SelecaoMaterialRepository;
import br.com.uniamerica.rentaclassroom.services.SelecaoMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/selecaomaterial")
public class SelecaoMaterialController {
    @Autowired
    private SelecaoMaterialRepository selecaoMaterialRepository;
    @Autowired
    private SelecaoMaterialService selecaoMaterialService;

    @GetMapping("/{id}")
    public ResponseEntity <?> findByIdPath(@PathVariable("id") final Long id){
        final SelecaoMaterial selecaoMaterial = this.selecaoMaterialRepository.findById(id).orElse(null);
        return selecaoMaterial == null ? ResponseEntity.badRequest().body("nenhum valor encontrado") : ResponseEntity.ok(selecaoMaterial);
    }
    @GetMapping
    public ResponseEntity <?> findByIdRequest(@RequestParam("id") final Long id){
        final SelecaoMaterial selecaoMaterial = this.selecaoMaterialRepository.findById(id).orElse(null);
        return selecaoMaterial == null ? ResponseEntity.badRequest().body("nenhum valor encontrado") : ResponseEntity.ok(selecaoMaterial);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> listaCompleta(){return ResponseEntity.ok(this.selecaoMaterialRepository.findAll());}

    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody @Validated final SelecaoMaterial selecaoMaterial){
        try{
            this.selecaoMaterialService.cadastraSelecaoMaterial(selecaoMaterial);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
        return ResponseEntity.ok("Registro realizado com sucesso");
    }

    @PutMapping
    public ResponseEntity <?> editar(@RequestParam("id") final Long id, @RequestBody @Validated final SelecaoMaterial selecaoMaterial){
        try{
            this.selecaoMaterialService.atualizaSelecaoMaterial(id, selecaoMaterial);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro editado com sucesso");
    }

}
