package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.Sala;
import br.com.uniamerica.rentaclassroom.repositories.SalaRepository;
import br.com.uniamerica.rentaclassroom.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/sala")
public class SalaController {
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private SalaService salaService;

    @GetMapping("/{id}")
    public ResponseEntity <?> findByIdPath(@PathVariable("id") final Long id){
        final Sala sala = this.salaRepository.findById(id).orElse(null);
        return sala == null ? ResponseEntity.badRequest().body("nenhum valor foi encontrado") : ResponseEntity.ok(sala);
    }
    @GetMapping
    public ResponseEntity<?> findByRequest(
            @RequestParam("id") final long id
    ) {
        final Sala sala = this.salaRepository.findById(id).orElse(null);
        return sala == null
                ? ResponseEntity.badRequest().body("nenhum valor foi encontrado")
                : ResponseEntity.ok(sala);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.salaRepository.findAll());
    }
     @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo(){
        return ResponseEntity.ok(this.salaRepository.findByAtivo(true));
     }

     @PostMapping
    public ResponseEntity<?> cadastar(@RequestBody @Validated final Sala sala) {
        try {
            this.salaService.cadastraSala(sala);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("error" + e.getMessage());
        }
         return ResponseEntity.ok().body("Registro realizado com sucesso");
     }

     @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final long id, @RequestBody @Validated final Sala sala) {
        try {
            this.salaService.atualizaSala(id, sala);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("error" + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("error" + e.getMessage());
        }
         return ResponseEntity.ok().body("Registro editar com sucesso");
     }

    @DeleteMapping
    public ResponseEntity <?> deletar(@RequestParam("id") final Long id){
        final Sala salaBanco = this.salaRepository.findById(id).orElse(null);
        try{
            this.salaRepository.delete(salaBanco);
        }
        catch(RuntimeException e){
            if(salaBanco.isAtivo()) {
                salaBanco.setAtivo(false);
                this.salaRepository.save(salaBanco);
                return ResponseEntity.internalServerError().body("flag desativada!");
            }
            return ResponseEntity.internalServerError().body("a flag ja está desativada");
        }
        return ResponseEntity.ok("Registro deletado");
    }

}
