package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Data;
import br.com.uniamerica.rentaclassroom.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    @Transactional
    public void cadastrarData(Data data){
        if(data.getHoraInicio() == null){
            throw new RuntimeException("o campo horaInicio não pode ser nulo");
        }
        if(data.getHoraFim() == null){
            throw new RuntimeException("o campo horaFim não pode ser nulo");
        }
        this.dataRepository.save(data);
    }

    @Transactional
    public void atualizarData(final Long id, Data data){
        final Data dataBanco = this.dataRepository.findById(id).orElse(null);

        if(dataBanco == null || !dataBanco.getId().equals(data.getId())){
            throw new RuntimeException("não foi possivel identificar o registro informado.");
        }
        this.dataRepository.save(data);
    }

    @Transactional
    public void deletaData(final Long id){
        final Data dataBanco = this.dataRepository.findById(id).orElse(null);
        if(dataBanco == null || !dataBanco.getId().equals(id)){
            throw new RuntimeException("registro não encontrado");
        }
        this.dataRepository.deleteById(id);
    }
}
