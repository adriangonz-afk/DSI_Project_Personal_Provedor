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
public class PersonalProyectosDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codPersonalProyecto;
    private Long codEmpleado;

    // Campos básicos
    private Long codPyto;
    private Long codCargo;
    private Long codArea;
    private Date fechaAsignacion; // LocalDate -> Date
    private Date fechaDesasignacion; // LocalDate -> Date
    private Double horasAsignadas;
}