package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Turma;
import br.com.uniamerica.rentaclassroom.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional
    public void cadastraTurma(Turma turma){
        if(turmaRepository.findById(turma.getId()).get().getCurso().equals(turma.getCurso())){
            throw new RuntimeException("O campo curso ja existe (Campo unico)");
        }
        if(turma.getCurso()==null){
            throw new RuntimeException("O campo curso esta em branco");
        }
        if(turma.getCurso().length()>50 || turma.getCurso().length()<4){
            throw new RuntimeException("O campo curso esta contem um tamanho ivalido");
        }
        if(turma.getQuantAlunos()==0){
            throw new RuntimeException("A quantidade de alunos esta invalida");
        }
        this.turmaRepository.save(turma);
    }

    @Transactional
    public void atualizaTurma(final Long id, Turma turma){
        final Turma turmaBanco = this.turmaRepository.findById(id).orElse(null);
        if(turmaBanco.getId() == null || !turmaBanco.getId().equals(turma.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro informado");
        }
        if(turma.getCadastro()==null || "".equals(turma.getCadastro())){
            turma.setCadastro(turmaRepository.findById(turma.getId()).get().getCadastro());
        }
        if(turmaRepository.findById(turma.getId()).get().getCurso().equals(turma.getCurso())){
            throw new RuntimeException("O campo curso ja existe (Campo unico)");
        }
        if(turma.getCurso()==null){
            throw new RuntimeException("O campo curso esta em branco");
        }
        if(turma.getCurso().length()>50 || turma.getCurso().length()<4){
            throw new RuntimeException("O campo curso esta contem um tamanho ivalido");
        }
        if(turma.getQuantAlunos()==0){
            throw new RuntimeException("A quantidade de alunos esta invalida");
        }
        this.turmaRepository.save(turma);
    }

}
