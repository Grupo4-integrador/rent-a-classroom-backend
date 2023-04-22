package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class AgendamentoId implements Serializable {
    private Integer professorId;
    private Integer ambienteId;
    private Integer selecaoMaterialId;
}
