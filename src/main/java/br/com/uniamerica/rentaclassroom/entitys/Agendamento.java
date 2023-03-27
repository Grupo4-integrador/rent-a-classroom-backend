package br.com.uniamerica.rentaclassroom.entitys;

import java.time.LocalDateTime;

public class Agendamento extends AbstractEntity{
    private Professor profesor;
    private Sala ambiente;
    private LocalDateTime data;
    private Periodo periodo;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private boolean solicitaMaterial;
    private SelecaoMaterial materiais;
    private Situacao situacao;
}