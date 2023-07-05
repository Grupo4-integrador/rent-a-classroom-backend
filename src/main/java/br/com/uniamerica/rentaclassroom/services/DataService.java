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
    public void cadastraData(Data data){
        if(data.getDataInicio() == null){
            throw new RuntimeException("o campo horaInicio não pode ser nulo");
        }
        if(data.getDataFim() == null){
            throw new RuntimeException("o campo horaFim não pode ser nulo");
        }
        this.dataRepository.save(data);
    }

    @Transactional
    public void atualizaData(final Long id, Data data){
        final Data dataBanco = this.dataRepository.findById(id).orElse(null);

        if(dataBanco == null || !dataBanco.getId().equals(data.getId())){
            throw new RuntimeException("não foi possivel identificar o registro informado.");
        }
       // if(data.getDataInicio() != dataRepository.findById(data.getId()).get().getDataInicio()){
         //   throw new RuntimeException("o campo dataInicio não pode ser alterado");
        //}
       // if(data.getDataFim() != dataRepository.findById(data.getId()).get().getDataFim()){
         //   throw new RuntimeException("o campo dataFim não pode ser alterado");
       // }
        if(data.getCadastro()==null || "".equals(data.getCadastro())){
            data.setCadastro(dataRepository.findById(data.getId()).get().getCadastro());
        }
        this.dataRepository.save(data);
    }
}
