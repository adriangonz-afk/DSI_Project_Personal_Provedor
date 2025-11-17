package com.system.entity;

import com.system.entity.id.ExperienciaLaboralId;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EXPERIENCIA_LABORAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienciaLaboral {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codEmpleado", column = @Column(name = "CODEMPLEADO")),
        @AttributeOverride(name = "codExperiencia", column = @Column(name = "CODEXPERIENCIA"))})
    private ExperienciaLaboralId id;

    @Column(name = "EMPRESA", nullable = false, length = 200)
    private String empresa;

    @Column(name = "ESPECIALIDAD", length = 100)
    private String especialidad;

    @Column(name = "FECHA_INICIO", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;

    @Lob
    @Column(name = "CERTIFICADO")
    private byte[] certificado;

    // Relación ManyToOne con Empleado (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODEMPLEADO", referencedColumnName = "CODEMPLEADO", insertable = false, updatable = false)
    })
    private Empleado empleado;
}
