package br.com.uniamerica.rentaclassroom.repository;

import br.com.uniamerica.rentaclassroom.entitys.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {}
