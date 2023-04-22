package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_agendamentos", schema = "rentaclassroom")
public class Agendamento extends AbstractEntity {

  @Getter
  @Setter
  @MapsId("professorId")
  @EmbeddedId
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "professor", nullable = false)
  private Professor professor;

  @Getter
  @Setter
  @MapsId("ambienteId")
  @EmbeddedId
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "ambiente", nullable = false)
  private Sala ambiente;

  @Getter
  @Setter
  @Column(name = "data", nullable = false)
  private LocalDateTime data;

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @JoinColumn(name = "periodo", nullable = false)
  private Periodo periodo;

  @Getter
  @Setter
  @Column(name = "hora_inicio", nullable = false)
  private LocalDateTime horaInicio;

  @Getter
  @Setter
  @Column(name = "hora_fim", nullable = false)
  private LocalDateTime horaFim;

  @Getter
  @Setter
  @Column(name = "solicita_material", nullable = false)
  private boolean solicitaMaterial;

  @Getter
  @Setter
  @MapsId("selecaoMaterialId")
  @EmbeddedId
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
          name = "tb_agendamento_materiais",
          schema = "rentaclassroom",
          uniqueConstraints = @UniqueConstraint(
                  columnNames = { "agendamento_id", "selecaomaterial_id" }
          ),
          joinColumns = @JoinColumn(name = "agendamento_id"),
          inverseJoinColumns = @JoinColumn(name = "selecaomaterial_id")
  )
  private List<SelecaoMaterial> selecaoMateriais;

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @JoinColumn(name = "situacao", nullable = false)
  private Situacao situacao;
}
