package br.com.uniamerica.rentaclassroom.repository;

import br.com.uniamerica.rentaclassroom.entitys.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
=======
public interface AgendamentoRepository
        extends JpaRepository<Agendamento, Long> {}
>>>>>>> e1916c0ecbe1b76ec33d91e338f7778abea68acb
