package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.Turma;
import br.com.uniamerica.rentaclassroom.repositories.TurmaRepository;
import br.com.uniamerica.rentaclassroom.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turma")
public class TurmaController {
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private TurmaService turmaService;

    @GetMapping("/{id}")
    public ResponseEntity <?> findByIdPath(@PathVariable("id") final Long id){
        final Turma turma = this.turmaRepository.findById(id).orElse(null);
        return turma == null ? ResponseEntity.badRequest().body("nenhum valor foi encontrado") : ResponseEntity.ok(turma);
    }
    @GetMapping
    public ResponseEntity<?> findByRequest(@RequestParam("id") final Long id){
        final Turma turma = this.turmaRepository.findById(id).orElse(null);
        return turma == null ? ResponseEntity.badRequest().body("nenhum valor encontrado") : ResponseEntity.ok(turma);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.turmaRepository.findAll());
    }
    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        return ResponseEntity.ok(this.turmaRepository.findByAtivo(true));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Turma turma) {
        try {
            this.turmaService.cadastraTurma(turma);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error" + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro realizado com sucesso");
    }
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody @Validated final Turma turma) {
        try {
            this.turmaService.atualizaTurma(id, turma);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error:" + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro editado com sucesso");
    }

    @DeleteMapping
    public ResponseEntity <?> deletar(@RequestParam("id") final Long id){
        final Turma turmaBanco = this.turmaRepository.findById(id).orElse(null);
        try{
            this.turmaRepository.delete(turmaBanco);
        }
        catch(RuntimeException e){
            if(turmaBanco.isAtivo()) {
                turmaBanco.setAtivo(false);
                this.turmaRepository.save(turmaBanco);
                return ResponseEntity.internalServerError().body("Erro no delete, flag desativada!");
            }
            return ResponseEntity.internalServerError().body("Erro no delete, a flag ja está desativada");
        }
        return ResponseEntity.ok("Registro deletado");
    }
}
