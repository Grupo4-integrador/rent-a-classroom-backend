package br.com.uniamerica.rentaclassroom.repositories;

import br.com.uniamerica.rentaclassroom.entitys.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
