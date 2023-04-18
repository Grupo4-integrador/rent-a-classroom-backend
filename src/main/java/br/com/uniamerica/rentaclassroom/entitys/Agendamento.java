package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_agendamento", schema = "rentaclassroom")
public class Agendamento extends AbstractEntity{
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "professor", nullable = false)
    private Professor professor;
    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ambiente", nullable = false)
    private Sala ambiente;
    @Getter @Setter
    @Column(name = "data", nullable = false)
    private LocalDateTime data;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "periodo", nullable = false)
    private Periodo periodo;
    @Getter @Setter
    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime horaInicio;
    @Getter @Setter
    @Column(name = "hora_fim", nullable = false)
    private LocalDateTime horaFim;
    @Getter @Setter
    @Column(name = "solicita_material", nullable = false)
    private boolean solicitaMaterial;
    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_selecao_material", nullable = false)
    private List<SelecaoMaterial> selecaoMaterial;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "situacao", nullable = false)
    private Situacao situacao;
}