package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ids", schema = "rentaclassroom")
public abstract class AbstractEntity {
    @Id
    @Getter @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Getter @Setter
    @Column(name = "cadastro", nullable = false)
    private LocalDateTime cadastro;
    @Setter @Getter
    @Column(name = "atualizacao", nullable = false)
    private LocalDateTime atualizacao;
    @Setter @Getter
    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @PrePersist
    public void dataCadastro(){
        this.setCadastro(LocalDateTime.now());
    }
    @PreUpdate
    public void dataAtualizacao(){
        this.setAtualizacao(LocalDateTime.now());
    }
}
