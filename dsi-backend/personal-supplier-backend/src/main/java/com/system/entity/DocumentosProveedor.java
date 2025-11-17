package com.system.entity;

import com.system.entity.id.DocumentosProveedorId;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DOCUMENTOS_PROVEEDOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentosProveedor {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codDocumento", column = @Column(name = "CODDOCUMENTO")),
        @AttributeOverride(name = "codProveedor", column = @Column(name = "CODPROVEEDOR")),
    })
    private DocumentosProveedorId id;

    @Column(name = "TIPO_DOCUMENTO", length = 50)
    private String tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO", length = 100)
    private String numeroDocumento;

    @Lob
    @Column(name = "ARCHIVO", nullable = false)
    private byte[] archivo;

    @Column(name = "TIPO_ARCHIVO", length = 10)
    private String tipoArchivo;

    @Column(name = "FECHA_EMISION")
    private LocalDate fechaEmision;

    @Column(name = "FECHA_VENCIMIENTO")
    private LocalDate fechaVencimiento;

    // Relación con PROVEEDOR (CodCIA, CodProveedor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODPROVEEDOR", referencedColumnName = "CODPROVEEDOR", insertable = false, updatable = false)
    })
    private Proveedor proveedor;
}
