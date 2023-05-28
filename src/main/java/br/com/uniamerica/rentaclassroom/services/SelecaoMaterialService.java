package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.SelecaoMaterial;
import br.com.uniamerica.rentaclassroom.repositories.SelecaoMaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelecaoMaterialService {
    @Autowired
    private SelecaoMaterialRepository selecaoMaterialRepository;
    @Transactional
    public void cadastraSelecaoMaterial(SelecaoMaterial selecaoMaterial){
        if(selecaoMaterial.getMaterial() == null){
            throw new RuntimeException("o campo material não pode ser nulo");
        }
        if(selecaoMaterial.getQuantidade() <= 0){
            throw new RuntimeException("o campo quantidade não pode ser menor ou igual a zero");
        }
        this.selecaoMaterialRepository.save(selecaoMaterial);
    }

    @Transactional
    public void atualizaSelecaoMaterial(final Long id, SelecaoMaterial selecaoMaterial){
        final SelecaoMaterial selecaoMaterialBanco = this.selecaoMaterialRepository.findById(id).orElse(null);
        if(selecaoMaterialBanco == null || !selecaoMaterialBanco.getId().equals(selecaoMaterial.getId())){
            throw new RuntimeException("não foi possível identificar o registro informado");
        }
        if(selecaoMaterial.getMaterial() == null){
            throw new RuntimeException("o campo material não pode ser nulo");
        }
        if(selecaoMaterial.getQuantidade() <= 0){
            throw new RuntimeException("o campo quantidade não pode ser menor ou igual a zero");
        }
        this.selecaoMaterialRepository.save(selecaoMaterial);
    }
}
