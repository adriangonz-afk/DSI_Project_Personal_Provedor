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
public class TareasPersonalDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codTarea;
    private Long codPyto;
    private Long codEmpleado;

    // Campos básicos
    private String nombre;
    private String descripcion;
    private Date fechaInicio; // LocalDate -> Date
    private Date fechaFin; // LocalDate -> Date
    private String estado;
}