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
        if("".equals(agendamento.getProfessor())){
            throw new RuntimeException("O campo professor não pode ser nulo");
        }
        if("".equals(agendamento.getAmbiente())){
            throw new RuntimeException("O campo ambiente não pode ser nulo");
        }
        if("".equals(agendamento.getData())){
            throw new RuntimeException("O campo data não pode ser nulo");
        }
        if("".equals(agendamento.getPeriodo())){
            throw new RuntimeException("O campo data não pode ser nulo");
        }
        if("".equals(agendamento.getHoraInicio())){
            throw new RuntimeException("O campo horaInicio não pode ser nulo");
        }
        if("".equals(agendamento.getHoraFim())){
            throw new RuntimeException("O campo horaFim não pode ser nulo");
        }
        if("".equals(agendamento.getSelecaoMateriais())){
            throw new RuntimeException("O campo selecaoMateriais não pode ser nulo");
        }
        if("".equals(agendamento.getSituacao())){
            throw new RuntimeException("O campo situacao não pode ser nulo");
        }
        this.agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void atualizaAgendamento(final Long id, Agendamento agendamento){
        final Agendamento agendamentoBanco = this.agendamentoRepository.findById(id).orElse(null);
        if(agendamentoBanco == null || !agendamentoBanco.getId().equals(agendamento.getId())){
            throw new RuntimeException("Não foi possível identificar o registro informado");
        }
        if("".equals(agendamento.getProfessor())){
            throw new RuntimeException("O campo professor não pode ser nulo");
        }
        if("".equals(agendamento.getAmbiente())){
            throw new RuntimeException("O campo ambiente não pode ser nulo");
        }
        if("".equals(agendamento.getData())){
            throw new RuntimeException("O campo data não pode ser nulo");
        }
        if("".equals(agendamento.getPeriodo())){
            throw new RuntimeException("O campo data não pode ser nulo");
        }
        if("".equals(agendamento.getHoraInicio())){
            throw new RuntimeException("O campo horaInicio não pode ser nulo");
        }
        if("".equals(agendamento.getHoraFim())){
            throw new RuntimeException("O campo horaFim não pode ser nulo");
        }
        if("".equals(agendamento.getSelecaoMateriais())){
            throw new RuntimeException("O campo selecaoMateriais não pode ser nulo");
        }
        if("".equals(agendamento.getSituacao())){
            throw new RuntimeException("O campo situacao não pode ser nulo");
        }
        this.agendamentoRepository.save(agendamento);
    }
}
