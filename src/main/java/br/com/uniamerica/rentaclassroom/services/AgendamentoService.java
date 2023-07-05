package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.entitys.Agendamento;
import br.com.uniamerica.rentaclassroom.repositories.AgendamentoRepository;
import br.com.uniamerica.rentaclassroom.repositories.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private SelecaoMaterialRepository selecaoMaterialRepository;

    @Transactional
    public void cadastraAgendamento(Agendamento agendamento){
       // if(agendamentoRepository.findById(agendamento.getUsuario().getId()).isEmpty()){
         //   throw new RuntimeException("o id do usuario inserido não existe");
        //}
        if(agendamentoRepository.findById(agendamento.getAmbiente().getId()).isEmpty()){
            throw new RuntimeException("o id do ambiente inserido não existe");
        }
        if(agendamento.getUsuario() == null){
            throw new RuntimeException("o campo usuario não pode ser nulo");
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
        this.agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void atualizaAgendamento(final Long id, Agendamento agendamento){
        final Agendamento agendamentoBanco = this.agendamentoRepository.findById(id).orElse(null);
        if(agendamentoBanco == null || !agendamentoBanco.getId().equals(agendamento.getId())){
            throw new RuntimeException("registro não encontrado");
        }
       // if(agendamento.getUsuario() != agendamentoRepository.findById(agendamento.getId()).get().getUsuario()){
         //   throw new RuntimeException("o campo usuario não pode ser alterado");
      //  }
        if(agendamento.getAmbiente() != agendamentoRepository.findById(agendamento.getId()).get().getAmbiente()){
            throw new RuntimeException("o campo ambiente não pode ser alterado");
        }
        if(agendamento.getData() != agendamentoRepository.findById(agendamento.getId()).get().getData()){
            throw new RuntimeException("o campo data não pode ser alterado");
        }
        if(agendamento.getPeriodo() != agendamentoRepository.findById(agendamento.getId()).get().getPeriodo()){
            throw new RuntimeException("o campo periodo não pode ser alterado");
        }
        if(agendamento.getCadastro()==null){
            agendamentoBanco.setCadastro(usuarioRepository.findById(agendamento.getId()).get().getCadastro());
        }
        this.agendamentoRepository.save(agendamento);
    }
}
