package br.com.uniamerica.rentaclassroom.repository;

import br.com.uniamerica.rentaclassroom.entitys.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    public List<Material> findByAtivo(@Param("ativo") final boolean ativo);
}