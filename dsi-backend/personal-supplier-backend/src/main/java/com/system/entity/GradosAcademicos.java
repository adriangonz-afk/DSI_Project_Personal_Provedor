package com.system.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.system.entity.id.GradosAcademicosId;

@Entity
@Table(name = "GRADOS_ACADEMICOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradosAcademicos {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codEmpleado", column = @Column(name = "CODEMPLEADO")),
        @AttributeOverride(name = "codGrado", column = @Column(name = "CODGRADO"))
    })
    private GradosAcademicosId id;

    @Column(name = "TIPO_GRADO", nullable = false, length = 50)
    private String tipoGrado;

    @Column(name = "CARRERA", nullable = false, length = 100)
    private String carrera;

    @Column(name = "TITULO", nullable = false, length = 200)
    private String titulo;

    @Column(name = "INSTITUCION", nullable = false, length = 200)
    private String institucion;

    @Column(name = "FECHA_OBTENCION")
    private LocalDate fechaObtencion;

    @Lob
    @Column(name = "DOCUMENTO")
    private byte[] documento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODEMPLEADO", referencedColumnName = "CODEMPLEADO", insertable = false, updatable = false)
    })
    private Empleado empleado;
}
