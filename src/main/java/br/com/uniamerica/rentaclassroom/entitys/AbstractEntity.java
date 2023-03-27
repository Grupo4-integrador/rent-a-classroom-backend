package br.com.uniamerica.rentaclassroom.entitys;

import java.time.LocalDateTime;

public abstract class AbstractEntity {
    private Long id;
    private LocalDateTime cadastro;
    private LocalDateTime edicao;
    private boolean ativo;
}
