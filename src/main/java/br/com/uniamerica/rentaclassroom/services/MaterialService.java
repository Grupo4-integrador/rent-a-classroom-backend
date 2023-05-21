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

    // Injeção de dependências de MaterialRepository por meio de método construtor.
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    // Método que válida a inserção de um novo material no banco de dados.
    @Transactional
    public void createMaterial(final Material material) {
        // Validações de nullable e length estão na entidade Material e são feitas pela anotação @Size

        // Validação de unique: valida se o nome do material não existe não banco de dados antes de salva-lô.
        List<Material> databaseMateriais = this.materialRepository.findAll();
        for (Material databaseMaterial : databaseMateriais) {
            Assert.isTrue(!material.getNome().equals(databaseMaterial.getNome()),
                    "Esse material já existe nos registros");
        }

        this.materialRepository.save(material);
    }

    // Método que valida a atualização de um material existente no banco de dados.
    @Transactional
    public void updateMaterial(final Long id, final Material material) {
        // Valida se o registro do material existe no banco de dados antes de atualiza-lô.
        final Material databaseMaterial = this.materialRepository.findById(id).orElse(null);
        if (databaseMaterial == null || !databaseMaterial.getId().equals(material.getId())) {
            throw new RuntimeException("Registro não encontrado");
        }

        // Validação de nullable: valida se o nome do material não é nulo.
        Assert.isTrue(material.getNome().length() > 0,
                "O nome do material não pode ser nulo");

        // Validação de length: valida se o tamanho do nome do material possui menos que 50  caractéres.
        Assert.isTrue(material.getNome().length() < 50,
                "O nome do material deve conter no máximo 50 caractéres");

        // Validação de unique: valida se o nome do material não existe não banco de dados antes de atualiza-lô.
        List<Material> databaseMateriais = this.materialRepository.findAll();
        for (Material databaseMaterialUpdate : databaseMateriais) {
            Assert.isTrue(!material.getNome().equals(databaseMaterialUpdate.getNome()),
                    "Esse material já existe nos registros");
        }

        this.materialRepository.save(material);
    }

    // Método que valida a existência de um material no banco de dados para poder exclui-lô.
    @Transactional
    public void deleteMaterial(final Long id) {

        Material databaseMaterial = this.materialRepository.findById(id).orElse(null);

        if (databaseMaterial == null || !databaseMaterial.getId().equals(id)) {
            throw new RuntimeException("Registro não encontrado");
        }

        this.materialRepository.deleteById(id);
    }
}
