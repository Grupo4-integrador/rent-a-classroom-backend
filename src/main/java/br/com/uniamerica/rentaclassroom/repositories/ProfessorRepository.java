package br.com.uniamerica.rentaclassroom.repositories;

import br.com.uniamerica.rentaclassroom.entitys.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {

    public List<Professor> findByAtivo(@Param("ativo")final boolean ativo);
    public Professor findByRa(Long ra);
}