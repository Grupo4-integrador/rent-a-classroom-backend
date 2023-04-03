package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_professor", schema = "rentaclassroom")
public class Professor extends AbstractEntity{
    @Getter
    @Column(name = "ra", nullable = false, unique = true)
    private Long ra;
    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Getter @Setter
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "turma", nullable = false)
    private Turma turma;
}
