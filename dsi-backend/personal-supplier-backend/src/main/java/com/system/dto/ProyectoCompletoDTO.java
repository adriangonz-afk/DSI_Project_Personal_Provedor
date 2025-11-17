package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyectoCompletoDTO {
    // Información básica del proyecto (clave compuesta plana)
    private Long codCia;
    private Long codPyto;
    
    // Campos básicos del proyecto
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
    
    // Información de la persona cliente relacionada
    private PersonaDTO cliente;
    
    // Listas de entidades relacionadas
    // Removed areas and cargosAreas as per new requirements
    
    // Listas filtradas por proyecto
    private List<EmpleadoCompletoDTO> empleados; // Empleados asignados al proyecto con toda su información
    private List<ProveedorCompletoDTO> proveedores; // Proveedores contratados para el proyecto con toda su información
}