package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  @Getter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Getter
  @Setter
  @Column(name = "cadastro", nullable = false)
  private LocalDateTime cadastro;

  @Getter
  @Setter
  @Column(name = "atualizacao")
  private LocalDateTime atualizacao;

  @Getter
  @Setter
  @Column(name = "ativo", nullable = false)
  private boolean ativo;

  @PrePersist
  public void dataCadastro() {
    this.setCadastro(LocalDateTime.now());
  }

  @PreUpdate
  public void dataAtualizacao() {
    this.setAtualizacao(LocalDateTime.now());
  }
}
