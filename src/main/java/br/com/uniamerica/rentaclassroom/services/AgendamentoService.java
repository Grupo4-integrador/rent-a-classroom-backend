package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Agendamento;
import br.com.uniamerica.rentaclassroom.repositories.AgendamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional
    public void cadastraAgendamento(Agendamento agendamento){
        if(agendamento.getProfessor() == null){
            throw new RuntimeException("o campo professor não pode ser nulo");
        }
        if(agendamento.getAmbiente() == null){
            throw new RuntimeException("o campo ambiente não pode ser nulo");
        }
        if(agendamento.getData() == null){
            throw new RuntimeException("o campo data não pode ser nulo");
        }
        if(agendamento.getPeriodo() == null){
            throw new RuntimeException("o campo período não pode ser nulo");
        }
        if(agendamento.getHoraInicio() == null){
            throw new RuntimeException("o campo horaInicio não pode ser nulo");
        }
        this.agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void atualizaAgendamento(final Long id, Agendamento agendamento){
        final Agendamento agendamentoBanco = this.agendamentoRepository.findById(id).orElse(null);
        if(agendamentoBanco == null || !agendamentoBanco.getId().equals(agendamento.getId())){
            throw new RuntimeException("não foi possível identificar o registro informado");
        }
        if(agendamento.getProfessor() == null){
            throw new RuntimeException("o campo professor não pode ser nulo");
        }
        if(agendamento.getAmbiente() == null){
            throw new RuntimeException("o campo ambiente não pode ser nulo");
        }
        if(agendamento.getData() == null){
            throw new RuntimeException("o campo data não pode ser nulo");
        }
        if(agendamento.getPeriodo() == null){
            throw new RuntimeException("o campo período não pode ser nulo");
        }
        if(agendamento.getHoraInicio() == null){
            throw new RuntimeException("o campo horaInicio não pode ser nulo");
        }
        this.agendamentoRepository.save(agendamento);
    }
}
