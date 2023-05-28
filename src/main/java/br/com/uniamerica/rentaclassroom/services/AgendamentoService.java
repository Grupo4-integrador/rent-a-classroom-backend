package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Agendamento;
import br.com.uniamerica.rentaclassroom.repositories.AgendamentoRepository;
import br.com.uniamerica.rentaclassroom.repositories.ProfessorRepository;
import br.com.uniamerica.rentaclassroom.repositories.SalaRepository;
import br.com.uniamerica.rentaclassroom.repositories.SelecaoMaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private SelecaoMaterialRepository selecaoMaterialRepository;

    @Transactional
    public void cadastraAgendamento(Agendamento agendamento){
        if(professorRepository.findById(agendamento.getProfessor().getId()).isEmpty()){
            throw new RuntimeException("o id do professor inserido não existe");
        }
        if(agendamento.getProfessor() == null){
            throw new RuntimeException("o campo professor não pode ser nulo");
        }
        if(salaRepository.findById(agendamento.getAmbiente().getId()).isEmpty()){
            throw new RuntimeException("o id do ambiente inserido não existe");
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
            throw new RuntimeException("registro não encontrado");
        }
        if(professorRepository.findById(agendamento.getProfessor().getId()).isEmpty()){
            throw new RuntimeException("o id do professor inserido não existe");
        }
        if(agendamento.getProfessor() == null){
            throw new RuntimeException("o campo professor não pode ser nulo");
        }
        if(salaRepository.findById(agendamento.getAmbiente().getId()).isEmpty()){
            throw new RuntimeException("o id do ambiente inserido não existe");
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
    public void deletaAgendamento(final Long id){
        final Agendamento agendamentoBanco = this.agendamentoRepository.findById(id).orElse(null);
        if(agendamentoBanco == null || !agendamentoBanco.getId().equals(id)){
            throw new RuntimeException("registro não encontrada");
        }
        this.agendamentoRepository.deleteById(id);
    }
}
