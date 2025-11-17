package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreasDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codArea;

    // Campos básicos
    private String nombre;
    private String descripcion;
    private String estado;
}
