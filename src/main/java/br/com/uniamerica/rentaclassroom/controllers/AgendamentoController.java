package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.Agendamento;
import br.com.uniamerica.rentaclassroom.repositories.AgendamentoRepository;
import br.com.uniamerica.rentaclassroom.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/{id}")
    public ResponseEntity <?> findByIdPath(@PathVariable("id") final Long id){
        final Agendamento agendamento = this.agendamentoRepository.findById(id).orElse(null);
        return agendamento == null ? ResponseEntity.badRequest().body("Nenhum valor foi encontrado") : ResponseEntity.ok(agendamento);
    }

    @GetMapping
    public ResponseEntity <?> findByIdRequest(@RequestParam("id") final Long id){
        final Agendamento agendamento = this.agendamentoRepository.findById(id).orElse(null);
        return agendamento == null ? ResponseEntity.badRequest().body("Nenhum valor foi encontrado") : ResponseEntity.ok(agendamento);
    }

    @GetMapping("/lista")
    public ResponseEntity <?> listaCompleta(){return ResponseEntity.ok(this.agendamentoRepository.findAll());}

    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final Agendamento agendamento){
        try{
            this.agendamentoService.cadastraAgendamento(agendamento);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro realizado com sucesso");
    }

    @PutMapping
    public ResponseEntity <?> editar(@RequestParam("id") final Long id, @RequestBody final Agendamento agendamento){
        try{
            this.agendamentoService.atualizaAgendamento(id, agendamento);
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
        final Agendamento agendamentoBanco = this.agendamentoRepository.findById(id).orElse(null);
        this.agendamentoRepository.delete(agendamentoBanco);
        return ResponseEntity.ok("Registro deletado com sucesso");
    }
}
