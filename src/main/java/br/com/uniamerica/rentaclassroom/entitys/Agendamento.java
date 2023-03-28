package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_agendamentos", schema = "rentaclassroom")
public class Agendamento extends AbstractEntity{
    @Setter @Getter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_professor", nullable = false)
    private Professor profesor;
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_ambiente", nullable = false)
    private Sala ambiente;
    @Getter @Setter
    @Column(name = "data", nullable = false)
    private LocalDateTime data;
    @Getter @Setter
    @JoinColumn(name = "tb_periodo", nullable = false)
    private Periodo periodo;
    @Getter @Setter
    @Column(name = "horaInicio", nullable = false)
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private boolean solicitaMaterial;
    private SelecaoMaterial materiais;
    private Situacao situacao;
}