package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "salas", schema = "public")
public class Sala extends AbstractEntity {
    
    @Getter @Setter
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;
    
    @Getter @Setter
    @Column(name = "capacidade", nullable = false)
    private int capacidade;
    
    @Getter @Setter
    @Column(name = "andar", nullable = false)
    private Andar andar;
    
    @Getter @Setter
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
