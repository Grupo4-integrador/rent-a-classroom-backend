package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.Data;
import br.com.uniamerica.rentaclassroom.repositories.DataRepository;
import br.com.uniamerica.rentaclassroom.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/agendamento")
public class DataController {
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private DataService dataService;

    @GetMapping("/{id}")
    public ResponseEntity <?> findByIdPath(@PathVariable("id") final Long id){
        final Data data = this.dataRepository.findById(id).orElse(null);
        return data == null ? ResponseEntity.badRequest().body("Nenhum registro foi encontrado") : ResponseEntity.ok(data);
    }

    @GetMapping
    public ResponseEntity <?> findByIdRequest(@RequestParam("id") final Long id){
        final Data data = this.dataRepository.findById(id).orElse(null);
        return data == null ? ResponseEntity.badRequest().body("Nenhum registro foi encontrado") : ResponseEntity.ok(data);
    }

    @GetMapping("/lista")
    public ResponseEntity <?> listaCompleta(){return ResponseEntity.ok(this.dataRepository.findAll());}

    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody @Validated final Data data){
        try{
            this.dataService.cadastrarData(data);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro realizado com sucesso");
    }

    @PutMapping
    public ResponseEntity <?> editar(@RequestParam("id") final Long id, @RequestBody @Validated final Data data){
        try{
            this.dataService.atualizarData(id, data);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    @DeleteMapping
    public ResponseEntity <?> deletar(@RequestParam("id") final Long id){
        final Data dataBanco = this.dataRepository.findById(id).orElse(null);
        try{
            this.dataRepository.delete(dataBanco);
        }
        catch(RuntimeException e){
            if(dataBanco.isAtivo()) {
                dataBanco.setAtivo(false);
                this.dataRepository.save(dataBanco);
                return ResponseEntity.internalServerError().body("Erro no delete, flag desativada!");
            }
            return ResponseEntity.internalServerError().body("Erro no delete, a flag ja está desativada");
        }
        return ResponseEntity.ok("Registro deletado");
    }
}
