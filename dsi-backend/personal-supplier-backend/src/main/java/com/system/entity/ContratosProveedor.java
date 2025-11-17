package com.system.entity;

import com.system.entity.id.ContratosProveedorId;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CONTRATOS_PROVEEDOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratosProveedor {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codProveedor", column = @Column(name = "CODPROVEEDOR")),
        @AttributeOverride(name = "codPyto", column = @Column(name = "CODPYTO")),
        @AttributeOverride(name = "codContrato", column = @Column(name = "CODCONTRATO"))
    })
    private ContratosProveedorId id;

    @Column(name = "NUMERO_CONTRATO", nullable = false, unique = true, length = 50)
    private String numeroContrato;

    @Column(name = "TIPO_CONTRATO", length = 50)
    private String tipoContrato;

    @Column(name = "FECHA_INICIO", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;

    @Column(name = "MONTO_TOTAL", nullable = false, precision = 15, scale = 2)
    private java.math.BigDecimal montoTotal;

    @Column(name = "MONEDA", length = 3)
    private String moneda;

    @Lob
    @Column(name = "DOCUMENTO_CONTRATO")
    private byte[] documentoContrato;

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
}
