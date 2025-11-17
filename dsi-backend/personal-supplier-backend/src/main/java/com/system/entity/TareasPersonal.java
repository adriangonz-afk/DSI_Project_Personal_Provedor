package com.system.entity;

import com.system.entity.id.TareasPersonalId;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TAREAS_PERSONAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TareasPersonal {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codTarea", column = @Column(name = "CODTAREA")),
        @AttributeOverride(name = "codPyto", column = @Column(name = "CODPYTO")),
        @AttributeOverride(name = "codEmpleado", column = @Column(name = "CODEMPLEADO"))
    })
    private TareasPersonalId id;

    @Column(name = "NOMBRE", nullable = false, length = 200)
    private String nombre;

    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;

    @Column(name = "ESTADO", length = 20)
    private String estado;

    // Relación ManyToOne con Proyecto (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODPYTO", referencedColumnName = "CODPYTO", insertable = false, updatable = false)
    })
    private Proyecto proyecto;

    // Relación ManyToOne con Empleado (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODEMPLEADO", referencedColumnName = "CODEMPLEADO", insertable = false, updatable = false)
    })
    private Empleado empleado;
}
