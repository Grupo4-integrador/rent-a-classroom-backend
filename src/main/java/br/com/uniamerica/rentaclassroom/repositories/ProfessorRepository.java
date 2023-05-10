package br.com.uniamerica.rentaclassroom.repositories;

import br.com.uniamerica.rentaclassroom.entitys.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    @Query("SELECT p FROM tb_professor p where p.ativo = true")
    List<Professor> findByAtivo();
    Professor findByRa(Long ra);
}