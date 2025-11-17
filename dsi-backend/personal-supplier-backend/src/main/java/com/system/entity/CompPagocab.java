package com.system.entity;

import com.system.entity.id.CompPagocabId;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "COMP_PAGOCAB")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CompPagocab {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codProveedor", column = @Column(name = "CODPROVEEDOR")),
        @AttributeOverride(name = "nroCP", column = @Column(name = "NROCP"))
    })
    private CompPagocabId id;

    @Column(name = "CODPYTO")
    private Long codPyto;

    @Column(name = "NROPAGO")
    private Integer nroPago;

    @Column(name = "TCOMPPAGO", length = 3)
    private String tCompPago;

    @Column(name = "ECOMPPAGO", length = 3)
    private String eCompPago;

    @Column(name = "FECCP")
    private LocalDate fecCP;

    @Column(name = "TMONEDA", length = 3)
    private String tMoneda;

    @Column(name = "EMONEDA", length = 3)
    private String eMoneda;

    @Column(name = "TIPCAMBIO", precision = 7, scale = 4)
    private BigDecimal tipCambio;

    @Column(name = "IMPMO", precision = 9, scale = 2)
    private BigDecimal impMO;

    @Column(name = "IMPNETOMN", precision = 9, scale = 2)
    private BigDecimal impNetoMN;

    @Column(name = "IMPIGVMN", precision = 9, scale = 2)
    private BigDecimal impIGVMN;

    @Column(name = "IMPTOTALMN", precision = 10, scale = 2)
    private BigDecimal impTotalMn;

    @Column(name = "FOTOCP", length = 60)
    private String fotoCP;

    @Column(name = "FOTOABONO", length = 60)
    private String fotoAbono;

    @Column(name = "FECABONO")
    private LocalDate fecAbono;

    @Column(name = "DESABONO", length = 1000)
    private String desAbono;

    @Column(name = "SEMILLA")
    private Integer semilla;

    @Column(name = "TABESTADO", length = 3)
    private String tabEstado;

    @Column(name = "CODESTADO", length = 3)
    private String codEstado;

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

    // Relación con ELEMENTOS (TMoneda, EMoneda)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "TMONEDA", referencedColumnName = "CODTAB", insertable = false, updatable = false),
        @JoinColumn(name = "EMONEDA", referencedColumnName = "CODELEM", insertable = false, updatable = false)
    })
    private Elementos monedaElementos;

    // Relación con ELEMENTOS (TCompPago, ECompPago)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "TCOMPPAGO", referencedColumnName = "CODTAB", insertable = false, updatable = false),
        @JoinColumn(name = "ECOMPPAGO", referencedColumnName = "CODELEM", insertable = false, updatable = false)
    })
    private Elementos compPagoElementos;
}
