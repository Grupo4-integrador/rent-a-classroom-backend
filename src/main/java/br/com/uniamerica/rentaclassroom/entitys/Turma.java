package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_turmas", schema = "rentaclassroom")
@AuditTable(value = "tb_turmas_audit", schema = "audit")
public class Turma extends AbstractEntity {

  @Getter
  @Setter
  @Column(name = "curso", nullable = false, unique = true, length = 50)
  private String curso;

  @Getter
  @Setter
  @Column(name = "quant_alunos", nullable = false)
  private int quantAlunos;
}
