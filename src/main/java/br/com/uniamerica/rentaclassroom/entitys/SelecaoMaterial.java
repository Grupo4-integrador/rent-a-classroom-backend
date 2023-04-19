package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_selecao_materiais", schema = "rentaclassroom")
public class SelecaoMaterial extends AbstractEntity {

  @Getter
  @Setter
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "material", nullable = false)
  private Material material;

  @Getter
  @Setter
  @Column(name = "quantidade", nullable = false)
  private int quantidade;
}
