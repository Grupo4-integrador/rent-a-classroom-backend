package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_usuarios", schema = "rentaclassroom")
@AuditTable(value = "tb_usuarios_audit", schema = "audit")
public class Usuario extends AbstractEntity {

  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @JoinColumn(name = "tb_role", nullable = false)
  private RoleUsuario roleUsuario;

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
  @OneToMany(fetch = FetchType.EAGER)
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
