package com.system.entity;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.system.entity.id.ProyectoId;

@Entity
@Table(name = "PROYECTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proyecto {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codPyto", column = @Column(name = "CODPYTO"))
    })
    private ProyectoId id;

    @Column(name = "NOMBPYTO", nullable = false, length = 1000)
    private String nombPyto;

    @Column(name = "EMPLJEFEPROY", nullable = false)
    private Long emplJefeProy;

    @Column(name = "CODCIA1", nullable = false)
    private Long codCia1;

    @Column(name = "CIACONTRATA", nullable = false)
    private Long ciaContrata;

    @Column(name = "CODCC", nullable = false)
    private Long codCC;

    @Column(name = "CODCLIENTE", nullable = false)
    private Long codCliente;

    @Column(name = "FLGEMPCONSORCIO", nullable = false, length = 1)
    private String flgEmpConsorcio;

    @Column(name = "CODSNIP", nullable = false, length = 10)
    private String codSNIP;

    @Column(name = "FECREG", nullable = false)
    private LocalDate fecReg;

    @Column(name = "CODFASE", nullable = false)
    private Integer codFase;

    @Column(name = "CODNIVEL", nullable = false)
    private Integer codNivel;

    @Column(name = "CODFUNCION", nullable = false, length = 4)
    private String codFuncion;

    @Column(name = "CODSITUACION", nullable = false)
    private Integer codSituacion;

    @Column(name = "NUMINFOR", nullable = false)
    private Integer numInfor;

    @Column(name = "NUMINFORENTRG", nullable = false)
    private Integer numInforEntrg;

    @Column(name = "ESTPYTO", nullable = false)
    private Integer estPyto;

    @Column(name = "FECESTADO", nullable = false)
    private LocalDate fecEstado;

    @Column(name = "VALREFER", nullable = false, precision = 12, scale = 2)
    private BigDecimal valRefer;

    @Column(name = "COSTODIRECTO", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoDirecto;

    @Column(name = "COSTOGGEN", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoGGen;

    @Column(name = "COSTOFINAN", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoFinan;

    @Column(name = "IMPUTILIDAD", nullable = false, precision = 12, scale = 2)
    private BigDecimal impUtilidad;

    @Column(name = "COSTOTOTSINIGV", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoTotSinIGV;

    @Column(name = "IMPIGV", nullable = false, precision = 12, scale = 2)
    private BigDecimal impIGV;

    @Column(name = "COSTOTOTAL", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoTotal;

    @Column(name = "COSTOPENALID", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoPenalid;

    @Column(name = "CODDPTO", nullable = false, length = 2)
    private String codDpto;

    @Column(name = "CODPROV", nullable = false, length = 2)
    private String codProv;

    @Column(name = "CODDIST", nullable = false, length = 2)
    private String codDist;

    @Column(name = "FECVIAB", nullable = false)
    private LocalDate fecViab;

    @Column(name = "RUTADOC", nullable = false, length = 300)
    private String rutaDoc;

    @Column(name = "ANNOINI", nullable = false)
    private Integer annoIni;

    @Column(name = "ANNOFIN", nullable = false)
    private Integer annoFin;

    @Column(name = "CODOBJC", nullable = false)
    private Integer codObjC;

    @Lob
    @Column(name = "LOGOPROY")
    private byte[] logoProy;

    @Column(name = "TABESTADO", nullable = false, length = 3)
    private String tabEstado;

    @Column(name = "CODESTADO", nullable = false, length = 3)
    private String codEstado;

    @Column(name = "OBSERVAC", nullable = false, length = 500)
    private String observac;

    @Column(name = "VIGENTE", nullable = false, length = 1)
    private String vigente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false)
    private Cia cia;

    // Relaciones foráneas compuestas (no implementadas aquí):
    // Empleado (CodCIA, EmplJefeProy), Cliente (CodCIA, CodCliente)
}
