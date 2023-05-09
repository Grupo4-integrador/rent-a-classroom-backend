package br.com.uniamerica.rentaclassroom.services;

import br.com.uniamerica.rentaclassroom.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
}
