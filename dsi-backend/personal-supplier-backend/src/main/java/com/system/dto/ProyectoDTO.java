package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyectoDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codPyto;

    // Campos básicos
    private String nombPyto;
    private Long emplJefeProy;
    private Long codCia1;
    private Long ciaContrata;
    private Long codCC;
    private Long codCliente;
    private String flgEmpConsorcio;
    private String codSNIP;
    private Date fecReg;
    private Integer codFase;
    private Integer codNivel;
    private String codFuncion;
    private Integer codSituacion;
    private Integer numInfor;
    private Integer numInforEntrg;
    private Integer estPyto;
    private Date fecEstado;
    private Double valRefer;
    private Double costoDirecto;
    private Double costoGGen;
    private Double costoFinan;
    private Double impUtilidad;
    private Double costoTotSinIGV;
    private Double impIGV;
    private Double costoTotal;
    private Double costoPenalid;
    private String codDpto;
    private String codProv;
    private String codDist;
    private Date fecViab;
    private String rutaDoc;
    private Integer annoIni;
    private Integer annoFin;
    private Integer codObjC;
    private byte[] logoProy;
    private String tabEstado;
    private String codEstado;
    private String observac;
    private String vigente;
}