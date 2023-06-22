package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_materiais", schema = "rentaclassroom")
@AuditTable(value = "tb_materiais_audit", schema = "audit")
public class Material extends AbstractEntity {

  @Size(min = 1, max = 50, message = "O nome do material deve conter mais que 1 caractére")
  @Getter
  @Setter
  @Column(name = "nome", nullable = false, unique = true, length = 50)
  private String nome;
  @Min(value = 1, message = "O material deve conter no minímo 1 quantidade")
  @Getter
  @Setter
  @Column(name = "quantidade", nullable = false)
  private int quantidade;
}
