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
public class EspecialidadesPersonalDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codEspecialidad;
    private Long codEmpleado;

    // Campos básicos
    private String especialidad;
    private byte[] certificado;
    private String institucion;
    private Date fechaObtencion; // LocalDate -> Date
    private Double horasCapacitacion;
}