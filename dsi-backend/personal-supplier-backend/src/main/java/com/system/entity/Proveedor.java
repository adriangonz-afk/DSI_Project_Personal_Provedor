package com.system.entity;

import com.system.entity.id.ProveedorId;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "PROVEEDOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codProveedor", column = @Column(name = "CODPROVEEDOR"))
    })
    private ProveedorId id;

    @Column(name = "NRORUC", nullable = false, length = 20)
    private String nroRuc;

    @Column(name = "VIGENTE", nullable = false, length = 1)
    private String vigente;

    // Relación ManyToOne con Persona (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODPROVEEDOR", referencedColumnName = "CODPERSONA", insertable = false, updatable = false)
    })
    private Persona persona;
}
