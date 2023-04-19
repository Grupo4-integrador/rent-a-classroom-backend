package br.com.uniamerica.rentaclassroom.repository;

import br.com.uniamerica.rentaclassroom.entitys.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {}
