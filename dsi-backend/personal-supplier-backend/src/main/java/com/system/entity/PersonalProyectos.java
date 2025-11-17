package com.system.entity;

import com.system.entity.id.PersonalProyectosId;
import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PERSONAL_PROYECTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalProyectos {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codPersonalProyecto", column = @Column(name = "CODPERSONALPROYECTO")),
        @AttributeOverride(name = "codEmpleado", column = @Column(name = "CODEMPLEADO")),
    })
    private PersonalProyectosId id;

    @Column(name = "CODPYTO", nullable = false)
    private Long codPyto;

    @Column(name = "CODCARGO", nullable = false)
    private Long codCargo;

    @Column(name = "CODAREA", nullable = false)
    private Long codArea;

    @Column(name = "FECHA_ASIGNACION")
    private LocalDate fechaAsignacion;

    @Column(name = "FECHA_DESASIGNACION")
    private LocalDate fechaDesasignacion;

    @Column(name = "HORAS_ASIGNADAS")
    private BigDecimal horasAsignadas;

    // Relación ManyToOne con Empleado (clave compuesta)
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

    // Relación ManyToOne con CargosAreas (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCARGO", referencedColumnName = "CODCARGO", insertable = false, updatable = false),
        @JoinColumn(name = "CODAREA", referencedColumnName = "CODAREA", insertable = false, updatable = false),
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false)
    })
    private CargosAreas cargosAreas;

    // Relación ManyToOne con Areas (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODAREA", referencedColumnName = "CODAREA", insertable = false, updatable = false)
    })
    private Areas areas;
}
