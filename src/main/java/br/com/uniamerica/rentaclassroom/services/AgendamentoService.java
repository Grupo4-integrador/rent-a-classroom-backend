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
        if(agendamento.getProfessor().getRa() == null){
            throw new RuntimeException("o campo ra do professor não pode ser nulo");
        }
        if(agendamento.getProfessor().getNome() == null){
            throw new RuntimeException("o campo nome do professor não pode ser nulo");
        }
        if(agendamento.getProfessor().getNome().length() > 50){
            throw new RuntimeException("o campo nome do professor não pode exceder o máximo de caractéres (50)");
        }
        if(agendamento.getProfessor().getEmail() == null){
            throw new RuntimeException("o campo email do professor não pode ser nulo");
        }
        if(agendamento.getProfessor().getEmail().length() > 100){
            throw new RuntimeException("o campo email do professor não pode exceder o máximo de caractéres (100)");
        }
        if(agendamento.getAmbiente().getNome() == null){
            throw new RuntimeException("o campo nome do ambiente não pode ser nulo");
        }
        if(agendamento.getAmbiente().getNome().length() > 50){
            throw new RuntimeException("o campo nome do ambiente não pode exceder o máximo de caractéres (50)");
        }
        if(agendamento.getAmbiente().getCapacidade() == 0){
            throw new RuntimeException("o campo capacidade do ambiente não pode ser igual a 0");
        }
        if(agendamento.getAmbiente().getAndar() == null){
            throw new RuntimeException("o campo andar do ambiente não pode ser nulo");
        }
        this.agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void atualizaAgendamento(final Long id, Agendamento agendamento){
        final Agendamento agendamentoBanco = this.agendamentoRepository.findById(id).orElse(null);
        if(agendamentoBanco == null || !agendamentoBanco.getId().equals(agendamento.getId())){
            throw new RuntimeException("não foi possível identificar o registro informado");
        }
        if(agendamento.getProfessor().getRa() == null){
            throw new RuntimeException("o campo ra do professor não pode ser nulo");
        }
        if(agendamento.getProfessor().getNome() == null){
            throw new RuntimeException("o campo nome do professor não pode ser nulo");
        }
        if(agendamento.getProfessor().getNome().length() > 50){
            throw new RuntimeException("o campo nome do professor não pode exceder o máximo de caractéres (50)");
        }
        if(agendamento.getProfessor().getEmail() == null){
            throw new RuntimeException("o campo email do professor não pode ser nulo");
        }
        if(agendamento.getProfessor().getEmail().length() > 100){
            throw new RuntimeException("o campo email do professor não pode exceder o máximo de caractéres (100)");
        }
        if(agendamento.getAmbiente().getNome() == null){
            throw new RuntimeException("o campo nome do ambiente não pode ser nulo");
        }
        if(agendamento.getAmbiente().getNome().length() > 50){
            throw new RuntimeException("o campo nome do ambiente não pode exceder o máximo de caractéres (50)");
        }
        if(agendamento.getAmbiente().getCapacidade() == 0){
            throw new RuntimeException("o campo capacidade do ambiente não pode ser igual a 0");
        }
        if(agendamento.getAmbiente().getAndar() == null){
            throw new RuntimeException("o campo andar do ambiente não pode ser nulo");
        }
        this.agendamentoRepository.save(agendamento);
    }
}
