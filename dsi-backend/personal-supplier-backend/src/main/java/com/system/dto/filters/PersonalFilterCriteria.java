package com.system.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalFilterCriteria {
    private String proyecto;
    private String area;
    private String cargo;
    private String universidad;
    private String carrera;
    private String especialidadCapacitacion;
    private BigDecimal horasMinimas;
    private BigDecimal horasMaximas;
    private String especialidadExperiencia;
    private Integer anosMinimos;
    private Integer anosMaximos;
}