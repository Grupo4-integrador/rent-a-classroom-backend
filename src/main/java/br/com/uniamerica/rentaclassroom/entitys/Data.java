package br.com.uniamerica.rentaclassroom.entitys;

import jakarta.persistence.*;
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
    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Getter @Setter
    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;
}
