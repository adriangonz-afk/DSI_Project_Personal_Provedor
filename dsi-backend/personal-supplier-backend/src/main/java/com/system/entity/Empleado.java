package com.system.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

import com.system.entity.id.EmpleadoId;

@Entity
@Table(name = "EMPLEADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codEmpleado", column = @Column(name = "CODEMPLEADO"))
    })
    private EmpleadoId id;

    @Column(name = "Direcc", nullable = false, length = 100)
    private String direcc;

    @Column(name = "Celular", nullable = false, length = 33)
    private String celular;

    @Column(name = "Hobby", nullable = false, length = 2000)
    private String hobby;

    @Lob
    @Column(name = "Foto")
    private byte[] foto;

    @Column(name = "FecNac", nullable = false)
    private LocalDate fecNac;

    @Column(name = "DNI", nullable = false, length = 20)
    private String dni;

    @Column(name = "NroCIP", nullable = false, length = 10)
    private String nroCIP;

    @Column(name = "FecCIPVig", nullable = false)
    private LocalDate fecCIPVig;

    @Column(name = "LicCond", nullable = false, length = 1)
    private String licCond;

    @Column(name = "FlgEmplIEA", nullable = false, length = 1)
    private String flgEmplIEA;

    @Column(name = "Observac", nullable = false, length = 300)
    private String observac;

    @Column(name = "CodCargo", nullable = false)
    private Integer codCargo;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @Column(name = "Vigente", nullable = false, length = 1)
    private String vigente;

    // Relación ManyToOne con Persona (clave foránea compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODEMPLEADO", referencedColumnName = "CODPERSONA", insertable = false, updatable = false)
    })
    private Persona persona;
}
