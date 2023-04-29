package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_sala", schema = "rentaclassroom")
@AuditTable(value = "tb_sala_audit")
public class Sala extends AbstractEntity {

  @Getter
  @Setter
  @Column(name = "nome", nullable = false, unique = true)
  private String nome;

  @Getter
  @Setter
  @Column(name = "capacidade", nullable = false)
  private int capacidade;

  @Getter
  @Setter
  @Enumerated(value = EnumType.STRING)
  @JoinColumn(name = "andar", nullable = false)
  private Andar andar;

  @Getter
  @Setter
  @Column(name = "descricao", nullable = false)
  private String descricao;
}
