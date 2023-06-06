package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Audited
@Table(name = "tb_datas", schema = "rentaclassroom")
@AuditTable(value = "tb_datas_audit", schema = "audit")
public class Data extends AbstractEntity {
    @Getter @Setter
    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime horaInicio;

    @Getter @Setter
    @Column(name = "hora_fim")
    private LocalDateTime horaFim;
}
