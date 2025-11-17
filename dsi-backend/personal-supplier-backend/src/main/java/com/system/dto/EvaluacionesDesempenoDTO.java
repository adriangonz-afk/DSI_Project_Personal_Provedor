package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluacionesDesempenoDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codEvaluacion;
    private Long codEmpleado;
    private Long codPyto;

    // Campos básicos
    private Long evaluadorId;
    private Double puntuacionTotal;
    private Double competenciasTecnicas;
    private Double competenciasBlandas;
    private Double cumplimientoObjetivos;
}