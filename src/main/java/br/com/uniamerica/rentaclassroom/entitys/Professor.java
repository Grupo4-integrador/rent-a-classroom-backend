package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_professores", schema = "rentaclassroom")
public class Professor extends AbstractEntity {

  @Getter
  @Column(name = "ra", unique = true)
  private Long ra;

  @Getter
  @Setter
  @Column(name = "nome", nullable = false, length = 50)
  private String nome;

  @Getter
  @Setter
  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;

  @Getter
  @Setter
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
    name = "tb_professor_turmas",
    schema = "rentaclassroom",
    uniqueConstraints = @UniqueConstraint(
      columnNames = { "professor_id", "turma_id" }
    ),
    joinColumns = @JoinColumn(name = "professor_id"),
    inverseJoinColumns = @JoinColumn(name = "turma_id")
  )
  private List<Turma> turmas;
}
