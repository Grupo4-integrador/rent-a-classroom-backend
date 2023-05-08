package br.com.uniamerica.rentaclassroom.repositories;

import br.com.uniamerica.rentaclassroom.entitys.SelecaoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelecaoMaterialRepository extends JpaRepository<SelecaoMaterial, Long> {}