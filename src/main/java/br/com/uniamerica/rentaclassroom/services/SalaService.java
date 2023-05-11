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

    }

    @Transactional
    public void atualizarSala(Sala sala){


    }

}
