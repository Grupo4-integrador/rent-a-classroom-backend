package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_selecao_materiais", schema = "rentaclassroom")
@AuditTable(value = "tb_selecao_materiais_audit", schema = "audit")
public class SelecaoMaterial extends AbstractEntity {

  @Getter @Setter
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "material", nullable = false)
  private Material material;

  @Getter @Setter
  @Column(name = "quantidade", nullable = false)
  private int quantidade;
}
