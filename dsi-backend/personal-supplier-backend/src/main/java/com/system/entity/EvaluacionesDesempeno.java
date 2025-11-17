package com.system.entity;

import java.math.BigDecimal;

import com.system.entity.id.EvaluacionesDesempenoId;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "EVALUACIONES_DESEMPENO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluacionesDesempeno {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codEmpleado", column = @Column(name = "CODEMPLEADO")),
        @AttributeOverride(name = "codPyto", column = @Column(name = "CODPYTO")),
        @AttributeOverride(name = "codEvaluacion", column = @Column(name = "CODEVALUACION"))
    })
    private EvaluacionesDesempenoId id;

    @Column(name = "EVALUADOR_ID", nullable = false)
    private Long evaluadorId;

    @Column(name = "PUNTUACION_TOTAL")
    private BigDecimal puntuacionTotal;

    @Column(name = "COMPETENCIAS_TECNICAS")
    private BigDecimal competenciasTecnicas;

    @Column(name = "COMPETENCIAS_BLANDAS")
    private BigDecimal competenciasBlandas;

    @Column(name = "CUMPLIMIENTO_OBJETIVOS")
    private BigDecimal cumplimientoObjetivos;

    // Relación ManyToOne con Empleado (evaluado, clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODEMPLEADO", referencedColumnName = "CODEMPLEADO", insertable = false, updatable = false)
    })
    private Empleado empleado;

    // Relación ManyToOne con Proyecto (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODPYTO", referencedColumnName = "CODPYTO", insertable = false, updatable = false)
    })
    private Proyecto proyecto;

    // Relación ManyToOne con Empleado (evaluador, clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "EVALUADOR_ID", referencedColumnName = "CODEMPLEADO", insertable = false, updatable = false)
    })
    private Empleado evaluador;
}
