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
        if(usuario.getSenha()==null){
            throw new RuntimeException("A senha do usuario está vazio");
        }
        /*if(this.usuarioRepository.findById(usuario.getId()).get().getSenha().equals(usuario.getSenha())){
            throw new RuntimeException("Este RA ja existe");
        }*/
        if(usuario.getNome()==null){
            throw new RuntimeException("O nome do usuario está vazio");
        }
        if(usuario.getNome().length()>50 || usuario.getNome().length()<4){
            throw new RuntimeException("O tamanho do nome do usuario esta invalido");
        }
        if(this.verificaMascara.email(usuario.getEmail())==false){
            throw new RuntimeException("O email está invalido");
        }
        if(usuario.getTurmas()==null){
            throw new RuntimeException("A(s) turma(s) do usuario está(ão) vázio(as)");
        }

        this.usuarioRepository.save(usuario);
    }
    public void atualizaUsuario(final Long id, Usuario usuario){
        final Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);
        if(usuarioBanco.getId()==null || usuarioBanco.getId().equals(usuario.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro");
        }
        if(usuario.getCadastro()==null || "".equals(usuario.getCadastro())){
            usuario.setCadastro(usuarioRepository.findById(usuario.getId()).get().getCadastro());
        }
        if(usuario.getSenha()==null){
            throw new RuntimeException("A senha do usuario está vazio");
        }
        if(usuario.getNome()==null){
            throw new RuntimeException("O nome do usuario está vazio");
        }
        if(usuario.getNome().length()>50 || usuario.getNome().length()<4){
            throw new RuntimeException("O tamanho do nome do usuario esta invalido");
        }
        if(this.verificaMascara.email(usuario.getEmail())==false){
            throw new RuntimeException("O email está invalido");
        }
        if(usuario.getTurmas()==null){
            throw new RuntimeException("A(s) turma(s) do usuario está(ão) vázio(as)");
        }

        this.usuarioRepository.save(usuario);
    }
}
