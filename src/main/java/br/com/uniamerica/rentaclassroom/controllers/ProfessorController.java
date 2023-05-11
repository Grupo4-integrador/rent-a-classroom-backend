package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.Professor;
import br.com.uniamerica.rentaclassroom.repositories.ProfessorRepository;
import br.com.uniamerica.rentaclassroom.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Professor professor = this.professorRepository.findById(id).orElse(null);
        return professor == null ?  ResponseEntity.badRequest().body("Nenhum valor foi encontrado") : ResponseEntity.ok(professor);
    }
    @GetMapping
    public ResponseEntity<?> findByIdRequest(@RequestParam("id") final Long id){
        final Professor professor = this.professorRepository.findById(id).orElse(null);
        return professor == null ? ResponseEntity.badRequest().body("Nenhum valor encontrado") : ResponseEntity.ok(professor);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){return ResponseEntity.ok(this.professorRepository.findAll());}
    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        return ResponseEntity.ok(this.professorRepository.findByAtivo(true));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Professor professor){
        try{
            this.professorService.cadastraProfessor(professor);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro realizado com sucesso");
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Professor professor){
        try{
           this.professorService.atualizaProfessor(id, professor);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        final Professor professor = this.professorRepository.findById(id).orElse(null);
        try{
            this.professorRepository.delete(professor);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(DataIntegrityViolationException e){
            professor.setAtivo(false);
            this.professorRepository.save(professor);
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
    }
}
