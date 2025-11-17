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
public class ExperienciaLaboralDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codExperiencia;
    private Long codEmpleado;

    // Campos básicos
    private String empresa;
    private String especialidad;
    private Date fechaInicio; // LocalDate -> Date
    private Date fechaFin; // LocalDate -> Date
    private byte[] certificado;
}