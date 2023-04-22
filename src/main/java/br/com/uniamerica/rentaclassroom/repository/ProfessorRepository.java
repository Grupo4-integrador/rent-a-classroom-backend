package br.com.uniamerica.rentaclassroom.repository;

import br.com.uniamerica.rentaclassroom.entitys.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}