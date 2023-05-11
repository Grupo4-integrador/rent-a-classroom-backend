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
        if ( sala.getCapacidade() == 0 ){
            throw new RuntimeException(" Deve inserir a capacidade da sala");
        }
        if ( "".equals(sala.getAndar())){
            throw new RuntimeException(" Deve inserir o andar");
        }
        this.salaRepository.save(sala);
    }

    @Transactional
    public void atualizarSala(final Long id, Sala sala){
        final Sala salaBanco = this.salaRepository.findById(id).orElse(null);

        if(salaBanco == null || !salaBanco.getId().equals(sala.getId())){
            throw new RuntimeException("nao foi possivel identificar o registro informado.");
        }
        if( "".equals(sala.getNome())){
            throw new RuntimeException(" Deve inserir o nome da sala");
        }
        this.salaRepository.save(sala);
    }

}
