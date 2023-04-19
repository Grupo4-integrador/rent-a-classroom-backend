package br.com.uniamerica.rentaclassroom.repository;

import br.com.uniamerica.rentaclassroom.entitys.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

@repository
public interface AgendamentoRepository
  extends JpaRepository<Agendamento, Long> {}
