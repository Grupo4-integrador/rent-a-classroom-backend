package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_agendamentos", schema = "rentaclassroom")
@AuditTable(value = "tb_agendamentos_audit", schema = "audit")
public class Agendamento extends AbstractEntity {

  @Getter @Setter
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "usuario", nullable = false)
  private Usuario usuario;

  @Getter @Setter
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ambiente", nullable = false)
  private Sala ambiente;

  @Getter @Setter
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tb_data", nullable = false)
  private Data data;

  @Getter @Setter
  @Enumerated(EnumType.STRING)
  @JoinColumn(name = "periodo", nullable = false)
  private Periodo periodo;

  @Getter @Setter
  @Column(name = "solicita_material", nullable = false)
  private boolean solicitaMaterial;

  @Getter @Setter
  @OneToMany(fetch = FetchType.EAGER)
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
  @Enumerated(EnumType.STRING)
  @JoinColumn(name = "situacao")
  private Situacao situacao;
}