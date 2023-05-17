package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_agendamento", schema = "rentaclassroom")
@AuditTable(value = "tb_agendamento_audit", schema = "audit")
public class Agendamento extends AbstractEntity {

  @Getter @Setter
  @NotNull(message = "o campo professor não pode ser nulo")
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "professor")
  private Professor professor;

  @Getter @Setter
  @NotNull(message = "o campo ambiente não pode ser nulo")
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "ambiente")
  private Sala ambiente;

  @Getter @Setter
  @NotNull(message = "o campo data não pode ser nulo")
  @Column(name = "data")
  private LocalDateTime data;

  @Getter @Setter
  @NotNull(message = "o campo periodo não pode ser nulo")
  @Enumerated(EnumType.STRING)
  @JoinColumn(name = "periodo")
  private Periodo periodo;

  @Getter @Setter
  @NotNull(message = "o campo horaInicio não pode ser nulo")
  @Column(name = "hora_inicio")
  private LocalDateTime horaInicio;

  @Getter @Setter
  @NotNull(message = "o campo horaFim não pode ser nulo")
  @Column(name = "hora_fim")
  private LocalDateTime horaFim;

  @Getter @Setter
  @Column(name = "solicita_material", nullable = false)
  private boolean solicitaMaterial;

  @Getter @Setter
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

  @Getter @Setter
  @NotNull(message = "o campo situacao não pode ser nulo")
  @Enumerated(EnumType.STRING)
  @JoinColumn(name = "situacao", nullable = false)
  private Situacao situacao;
}