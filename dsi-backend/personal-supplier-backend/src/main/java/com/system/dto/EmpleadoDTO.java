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
public class EmpleadoDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codEmpleado;

    // Campos básicos
    private String direcc;
    private String celular;
    private String hobby;
    private byte[] foto;
    private Date fecNac; // LocalDate -> Date
    private String dni;
    private String nroCIP;
    private Date fecCIPVig; // LocalDate -> Date
    private String licCond;
    private String flgEmplIEA;
    private String observac;
    private Integer codCargo;
    private String email;
    private String vigente;
}