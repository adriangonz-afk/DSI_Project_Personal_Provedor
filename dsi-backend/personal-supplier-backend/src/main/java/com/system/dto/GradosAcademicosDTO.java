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
public class GradosAcademicosDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codGrado;
    private Long codEmpleado;

    // Campos básicos
    private String tipoGrado;
    private String carrera;
    private String titulo;
    private String institucion;
    private Date fechaObtencion; // LocalDate -> Date
    private byte[] documento;
}