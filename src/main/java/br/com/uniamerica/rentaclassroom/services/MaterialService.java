package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Material;
import br.com.uniamerica.rentaclassroom.repositories.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MaterialService {
    final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Transactional
    public void createMaterial(final Material material) {
        Assert.isTrue(material.getNome().length() > 0,
                "O nome do material não pode ser nulo");
        Assert.isTrue(material.getNome().length() < 50,
                "O nome do material deve conter no máximo 50 caractéres");

        List<Material> databaseMateriais = this.materialRepository.findAll();
        for (Material databaseMaterial : databaseMateriais) {
            Assert.isTrue(!material.getNome().equals(databaseMaterial.getNome()),
                    "Esse material já existe nos registros");
        }

        this.materialRepository.save(material);
    }

    @Transactional
    public void updateMaterial(final Long id, final Material material) {
        final Material databaseMaterial = this.materialRepository.findById(id).orElse(null);
        if (databaseMaterial == null || !databaseMaterial.getId().equals(material.getId())) {
            throw new RuntimeException("Registro não encontrado");
        }

        Assert.isTrue(material.getNome().length() > 0,
                "O nome do material não pode ser nulo");
        Assert.isTrue(material.getNome().length() < 50,
                "O nome do material deve conter no máximo 50 caractéres");

        List<Material> databaseMateriais = this.materialRepository.findAll();
        for (Material databaseMaterialUpdate : databaseMateriais) {
            Assert.isTrue(!material.getNome().equals(databaseMaterialUpdate.getNome()),
                    "Esse material já existe nos registros");
        }

        this.materialRepository.save(material);
    }

    @Transactional
    public void deleteMaterial(final Long id) {

        Material databaseMaterial = this.materialRepository.findById(id).orElse(null);

        if (databaseMaterial == null || !databaseMaterial.getId().equals(id)) {
            throw new RuntimeException("Registro não encontrado");
        }

        this.materialRepository.deleteById(id);
    }
}
