package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_agendamentos", schema = "rentaclassroom")
public class Agendamento extends AbstractEntity{
    @Getter @Setter
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
    @Enumerated(EnumType.STRING)
    @Column(name = "tb_periodo", nullable = false, length = 20)
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
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tb_agendamento_materiais", schema = "rentaclassroom",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "agendamento_id",
                            "selecaomaterial_id"
                    }
            ),
            joinColumns =    @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "selecaomaterial_id")
    )

    private List<SelecaoMaterial> materiais;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false, length = 20)
    private Situacao situacao;
}