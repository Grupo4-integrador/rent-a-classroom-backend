package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_material", schema = "rentaclassroom")
@AuditTable(value = "tb_material_audit")
public class Material extends AbstractEntity {

  @Getter
  @Setter
  @Column(name = "nome", nullable = false, unique = true, length = 50)
  private String nome;
}
