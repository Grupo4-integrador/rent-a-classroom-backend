package br.com.uniamerica.rentaclassroom.controllers;

import br.com.uniamerica.rentaclassroom.entitys.Usuario;
import br.com.uniamerica.rentaclassroom.repositories.UsuarioRepository;
import br.com.uniamerica.rentaclassroom.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        return usuario == null ?  ResponseEntity.badRequest().body("nenhum valor foi encontrado") : ResponseEntity.ok(usuario);
    }
    @GetMapping
    public ResponseEntity<?> findByIdRequest(@RequestParam("id") final Long id){
        final Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        return usuario == null ? ResponseEntity.badRequest().body("nenhum valor encontrado") : ResponseEntity.ok(usuario);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){return ResponseEntity.ok(this.usuarioRepository.findAll());}
    @GetMapping("/ativo")
    public ResponseEntity<?> findByAtivo() {
        return ResponseEntity.ok(this.usuarioRepository.findByAtivo(true));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Usuario usuario){
        try{
            this.usuarioService.cadastraUsuario(usuario);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro realizado com sucesso");
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody @Validated final Usuario usuario){
        try{
           this.usuarioService.atualizaUsuario(id, usuario);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Erro " + e.getCause().getCause().getMessage());
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Erro " + e.getMessage());
        }
        return ResponseEntity.ok("Registro editado com sucesso");
    }

    @DeleteMapping
    public ResponseEntity <?> deletar(@RequestParam("id") final Long id){
        final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);
        try{
            this.usuarioRepository.delete(usuarioBanco);
        }
        catch(RuntimeException e){
            if(usuarioBanco.isAtivo()) {
                usuarioBanco.setAtivo(false);
                this.usuarioRepository.save(usuarioBanco);
                return ResponseEntity.internalServerError().body("flag desativada!");
            }
            return ResponseEntity.internalServerError().body("a flag ja está desativada");
        }
        return ResponseEntity.ok("Registro deletado");
    }
}
