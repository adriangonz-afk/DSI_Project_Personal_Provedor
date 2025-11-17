package com.system.entity;

import com.system.entity.id.ServiciosProveedorId;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "SERVICIOS_PROVEEDOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiciosProveedor {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codServicio", column = @Column(name = "CODSERVICIO")),
        @AttributeOverride(name = "codProveedor", column = @Column(name = "CODPROVEEDOR"))
    })
    private ServiciosProveedorId id;

    @Column(name = "NOMBRE_SERVICIO", nullable = false, length = 200)
    private String nombreServicio;

    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Lob
    @Column(name = "DOCUMENTO_SERVICIO")
    private byte[] documentoServicio;

    // Relación ManyToOne con Proveedor (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODPROVEEDOR", referencedColumnName = "CODPROVEEDOR", insertable = false, updatable = false)
    })
    private Proveedor proveedor;
}
