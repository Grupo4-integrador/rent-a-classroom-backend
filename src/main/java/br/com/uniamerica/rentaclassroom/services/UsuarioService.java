package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.config.VerificaMascara;
import br.com.uniamerica.rentaclassroom.entitys.Usuario;
import br.com.uniamerica.rentaclassroom.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VerificaMascara verificaMascara;

    @Transactional
    public void cadastraUsuario(Usuario usuario){
        if(this.usuarioRepository.findById(usuario.getId()).get().getSenha().equals(usuario.getSenha())){
            throw new RuntimeException("Este RA ja existe");
        }
        if(usuario.getCor()==null){
            throw new RuntimeException("o campo cor não pode ser nulo");
        }
        if(usuario.getNome()==null || "".equals(usuario.getNome())){
            throw new RuntimeException("o campo nome não pode ser nulo ou vazio");
        }
        if(usuario.getNome().length()>50 || usuario.getNome().length()<4){
            throw new RuntimeException("o campo nome não condiz com a quantidade de caractéres necessárias (4 ~ 50)");
        }
        if(usuario.getSenha()==null || "".equals(usuario.getSenha())){
            throw new RuntimeException("o campo senha não pode ser nulo ou vazio");
        }
        if(!this.verificaMascara.email(usuario.getEmail())){
            throw new RuntimeException("o campo email não é válido");
        }
        if(usuario.getTurmas()==null){
            throw new RuntimeException("o campo turma não pode ser nulo");
        }
        this.usuarioRepository.save(usuario);
    }
    public void atualizaUsuario(final Long id, Usuario usuario){
        final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);
        if(usuarioBanco.getId()==null || usuarioBanco.getId().equals(usuario.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro");
        }
        if(usuario.getCor()==null){
            throw new RuntimeException("o campo cor não pode ser nulo");
        }
        if(usuario.getNome() != usuarioRepository.findById(usuario.getId()).get().getNome()){
            throw new RuntimeException("o campo nome não pode ser alterado");
        }
        if(usuario.getSenha()==null || "".equals(usuario.getSenha())){
            throw new RuntimeException("o campo senha não pode ser nulo ou vazio");
        }
        if(!this.verificaMascara.email(usuario.getEmail())){
            throw new RuntimeException("o campo email não é válido");
        }
        if(usuario.getTurmas()==null){
            throw new RuntimeException("o campo turma não pode ser nulo");
        }
        if(usuario.getCadastro()==null || "".equals(usuario.getCadastro())){
            usuario.setCadastro(usuarioRepository.findById(usuario.getId()).get().getCadastro());
        }
        this.usuarioRepository.save(usuario);
    }
}
