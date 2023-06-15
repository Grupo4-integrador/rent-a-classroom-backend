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
    public void cadastraSala(Sala sala){
        if(sala.getNome() == null || "".equals(sala.getNome())){
            throw new RuntimeException("o campo nome não pode ser nulo ou vazio");
        }
        if(salaRepository.findByNome(sala.getNome()) != null){
            throw new RuntimeException("o campo sala já existe");
        }
        if(sala.getNome().length()>50 || sala.getNome().length()<4){
            throw new RuntimeException("o campo nome não condiz com a quantidade de caractéres necessárias (4 ~ 50)");
        }
        if(sala.getCapacidade() == 0){
            throw new RuntimeException("o campo capacidade não pode ser nulo");
        }
        if(sala.getAndar() == null){
            throw new RuntimeException("o campo andar não pode ser nulo");
        }
        this.salaRepository.save(sala);
    }

    @Transactional
    public void atualizaSala(final Long id, Sala sala){
        final Sala salaBanco = this.salaRepository.findById(id).orElse(null);

        if(salaBanco == null || !salaBanco.getId().equals(sala.getId())){
            throw new RuntimeException("nao foi possivel identificar o registro informado.");
        }
        if(sala.getNome() == null || "".equals(sala.getNome())){
            throw new RuntimeException("o campo nome não pode ser nulo ou vazio");
        }
        if(sala.getNome().length()>50 || sala.getNome().length()<4){
            throw new RuntimeException("o campo nome não condiz com a quantidade de caractéres necessárias (4 ~ 50)");
        }
        if(sala.getCapacidade() == 0){
            throw new RuntimeException("o campo capacidade não pode ser nulo");
        }
        if(sala.getAndar() == null){
            throw new RuntimeException("o campo andar não pode ser nulo");
        }
        if(sala.getCadastro()==null){
            sala.setCadastro(salaRepository.findById(sala.getId()).get().getCadastro());
        }
        this.salaRepository.save(sala);
    }
}
