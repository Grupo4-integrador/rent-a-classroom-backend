package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.SelecaoMaterial;
import br.com.uniamerica.rentaclassroom.repositories.SelecaoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/selecaomaterial")
public class SelecaoMaterialController {
    @Autowired
    private SelecaoMaterialRepository selecaoMaterialRepository;

    @GetMapping("/{id}")
    public ResponseEntity <?> findByIdPath(@PathVariable("id") final Long id){
        final SelecaoMaterial selecaoMaterial = this.selecaoMaterialRepository.findById(id).orElse(null);
        return selecaoMaterial == null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(selecaoMaterial);
    }

    @GetMapping
    public ResponseEntity <?> findByIdRequest(@RequestParam("id") final Long id){
        final SelecaoMaterial selecaoMaterial = this.selecaoMaterialRepository.findById(id).orElse(null);
        return selecaoMaterial == null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(selecaoMaterial);
    }

    @GetMapping("/lista")
    public ResponseEntity <?> listaCompleta(){return ResponseEntity.ok(this.selecaoMaterialRepository.findAll());}

    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final SelecaoMaterial selecaoMaterial){
        try{
            this.selecaoMaterialRepository.save(selecaoMaterial);
            return ResponseEntity.ok("Registro realizado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity <?> editar(@RequestParam("id") final Long id, @RequestBody final SelecaoMaterial selecaoMaterial){
        try{
            final SelecaoMaterial selecaoMaterialBanco = this.selecaoMaterialRepository.findById(id).orElse(null);
            if(selecaoMaterialBanco == null || !selecaoMaterialBanco.getId().equals(selecaoMaterial.getId())){
                throw new RuntimeException("Não foi possível identificar o registro informado");
            }
            this.selecaoMaterialRepository.save(selecaoMaterialBanco);
            return ResponseEntity.ok("Registro atualizado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity <?> deletar(@RequestParam("id") final Long id){
        final SelecaoMaterial selecaoMaterialBanco = this.selecaoMaterialRepository.findById(id).orElse(null);
        this.selecaoMaterialRepository.delete(selecaoMaterialBanco);
        return ResponseEntity.ok("Registro deletado com sucesso");
    }
}
