package com.system.entity;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.system.entity.id.EspecialidadesPersonalId;

@Entity
@Table(name = "ESPECIALIDADES_PERSONAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadesPersonal {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codEmpleado", column = @Column(name = "CODEMPLEADO")),
        @AttributeOverride(name = "codEspecialidad", column = @Column(name = "CODESPECIALIDAD"))
    })
    private EspecialidadesPersonalId id;

    @Column(name = "ESPECIALIDAD", nullable = false, length = 100)
    private String especialidad;

    @Lob
    @Column(name = "CERTIFICADO")
    private byte[] certificado;

    @Column(name = "INSTITUCION", length = 200)
    private String institucion;

    @Column(name = "FECHA_OBTENCION")
    private LocalDate fechaObtencion;

    @Column(name = "HORAS_CAPACITACION", columnDefinition = "NUMBER")
    private BigDecimal horasCapacitacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODEMPLEADO", referencedColumnName = "CODEMPLEADO", insertable = false, updatable = false)
    })
    private Empleado empleado;
}
