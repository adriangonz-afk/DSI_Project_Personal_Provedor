package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiciosProveedorDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codServicio;
    private Long codProveedor;

    // Campos básicos
    private String nombreServicio;
    private String descripcion;
    private byte[] documentoServicio;
}