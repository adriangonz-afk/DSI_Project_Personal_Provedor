package com.system.entity;

import com.system.entity.id.ActividadesProveedorId;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "ACTIVIDADES_PROVEEDOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActividadesProveedor {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codActividad", column = @Column(name = "CODACTIVIDAD")),
        @AttributeOverride(name = "codProveedor", column = @Column(name = "CODPROVEEDOR")),
        @AttributeOverride(name = "codPyto", column = @Column(name = "CODPYTO")),
        @AttributeOverride(name = "codContrato", column = @Column(name = "CODCONTRATO"))
    })
    private ActividadesProveedorId id;

    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "FECHA_ACTIVIDAD")
    private LocalDate fechaActividad;

    @Column(name = "MONTO", precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(name = "ESTADO", length = 20)
    private String estado;

    @Lob
    @Column(name = "DOCUMENTO")
    private byte[] documento;

    @Column(name = "OBSERVACIONES", length = 1000)
    private String observaciones;

    // Relación con PROVEEDOR (CodCIA, CodProveedor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODPROVEEDOR", referencedColumnName = "CODPROVEEDOR", insertable = false, updatable = false)
    })
    private Proveedor proveedor;

    // Relación con PROYECTO (CodCIA, CodPyto)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODPYTO", referencedColumnName = "CODPYTO", insertable = false, updatable = false)
    })
    private Proyecto proyecto;

    // Relación con CONTRATOS_PROVEEDOR (CodCIA, CodContrato, CodProveedor, CodPyto)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODCONTRATO", referencedColumnName = "CODCONTRATO", insertable = false, updatable = false),
        @JoinColumn(name = "CODPROVEEDOR", referencedColumnName = "CODPROVEEDOR", insertable = false, updatable = false),
        @JoinColumn(name = "CODPYTO", referencedColumnName = "CODPYTO", insertable = false, updatable = false)
    })
    private ContratosProveedor contratosProveedor;
}
