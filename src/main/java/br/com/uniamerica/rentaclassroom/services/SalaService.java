package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Sala;
import br.com.uniamerica.rentaclassroom.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Transactional
    public void cadastrarSala(Sala sala){
        if( "".equals(sala.getNome())){
            throw new RuntimeException(" Deve inserir o nome da sala");
        }
        if ( salaRepository.findByNome(sala.getNome()) != null){
            throw new RuntimeException(" A sala que esta querendo inserir ja existe");
        }

    }

    @Transactional
    public void atualizarSala(Sala sala){


    }

}
