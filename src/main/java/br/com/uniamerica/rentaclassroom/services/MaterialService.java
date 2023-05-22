package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Material;
import br.com.uniamerica.rentaclassroom.repositories.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        Material databaseMaterial = this.materialRepository.findByNome(material.getNome());
        Assert.isTrue(databaseMaterial == null || !databaseMaterial.getNome().equals(material.getNome()),
                "Esse material já existe nos registros");

        this.materialRepository.save(material);
    }

    // Método que valida a atualização de um material existente no banco de dados.
    @Transactional
    public void updateMaterial(final Long id, final Material material) {
        // Valida se o registro do material existe no banco de dados antes de atualiza-lô.
        Material databaseMaterial = this.materialRepository.findById(id).orElse(null);
        if (databaseMaterial == null || !databaseMaterial.getId().equals(material.getId())) {
            throw new RuntimeException("Registro não encontrado");
        }

        // Validação de unique: valida se o nome do material não existe não banco de dados antes de atualiza-lô.

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
