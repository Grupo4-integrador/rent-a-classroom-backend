package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Professor;
import br.com.uniamerica.rentaclassroom.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

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
        if(professor.getEmail()==null){
            throw new RuntimeException("O email do professor está vazio");
        }
        if(professor.getEmail().length()>100){
            throw new RuntimeException("O email do professor ultrapassou o limite (100 caracteres)");
        }
    }
}
