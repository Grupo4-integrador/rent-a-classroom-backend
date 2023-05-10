package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.config.VerificaMascara;
import br.com.uniamerica.rentaclassroom.entitys.Professor;
import br.com.uniamerica.rentaclassroom.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private VerificaMascara verificaMascara;

    @Transactional
    public void cadastraProfessor(Professor professor){
        if(professor.getRa()==null){
            throw new RuntimeException("O RA do professor está vazio");
        }
        if(this.professorRepository.findByRa(professor.getRa())!=null){
            throw new RuntimeException("Este RA ja existe");
        }
        if(professor.getNome()==null){
            throw new RuntimeException("O nome do professor está vazio");
        }
        if(professor.getNome().length()>50){
            throw new RuntimeException("O nome do professor ultrapassou o limite (50 caracteres)");
        }
        if(this.verificaMascara.email(professor.getEmail())==false){
            throw new RuntimeException("O email está invalido");
        }
        if(professor.getTurmas()==null){
            throw new RuntimeException("A(s) turma(s) do professor está(ão) vázio(as)");
        }

        this.professorRepository.save(professor);
    }
    public void atualizaProfessor(final Long id, Professor professor){
        final Professor professorBanco = this.professorRepository.findById(id).orElse(null);
        if(professorBanco.getId()==null || professorBanco.getId().equals(professor.getId())){
            throw new RuntimeException("Não foi possivel encontrar o registro");
        }
        if(professor.getRa()==null){
            throw new RuntimeException("O RA do professor está vazio");
        }
        if(this.professorRepository.findByRa(professor.getRa())!=null){
            throw new RuntimeException("Este RA ja existe");
        }
        if(professor.getNome()==null){
            throw new RuntimeException("O nome do professor está vazio");
        }
        if(professor.getNome().length()>50){
            throw new RuntimeException("O nome do professor ultrapassou o limite (50 caracteres)");
        }
        if(this.verificaMascara.email(professor.getEmail())==false){
            throw new RuntimeException("O email está invalido");
        }
        if(professor.getTurmas()==null){
            throw new RuntimeException("A(s) turma(s) do professor está(ão) vázio(as)");
        }

        this.professorRepository.save(professor);
    }
}
