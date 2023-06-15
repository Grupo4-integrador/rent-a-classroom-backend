package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_usuario", schema = "rentaclassroom")
@AuditTable(value = "tb_usuario_audit", schema = "audit")
public class Usuario extends AbstractEntity {

  @Getter
  @Setter
  @Column(name = "role", nullable = false)
  private RoleUsuario role;

  @Getter
  @Setter
  @Column(name = "nome", nullable = false, length = 50)
  private String nome;

  @Getter @Setter
  @Column(name = "senha", unique = true)
  private String senha;

  @Getter
  @Setter
  @Column(name = "email", unique = true, length = 100)
  private String email;

  @Getter
  @Setter
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "tb_usuario_turmas",
    schema = "rentaclassroom",
    uniqueConstraints = @UniqueConstraint(
      columnNames = { "usuario_id", "turma_id" }
    ),
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "turma_id")
  )
  private List<Turma> turmas;
}
