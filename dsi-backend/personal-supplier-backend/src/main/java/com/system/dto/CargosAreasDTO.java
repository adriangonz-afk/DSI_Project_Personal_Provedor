package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargosAreasDTO {
    // Clave compuesta (plano)
    private Long codCargo;
    private Long codArea;
    private Long codCia;

    // Campos básicos
    private String nombreCargo;
}