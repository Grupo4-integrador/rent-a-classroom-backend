package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_selecao_material", schema = "rentaclassroom")
public class SelecaoMaterial extends AbstractEntity {

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_materiais", nullable = false)
    private Material materiais;
    @Getter @Setter
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
}
