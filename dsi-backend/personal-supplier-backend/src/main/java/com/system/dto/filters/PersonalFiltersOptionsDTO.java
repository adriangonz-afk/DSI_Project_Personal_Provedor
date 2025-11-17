package com.system.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalFiltersOptionsDTO {
    private List<String> proyectos;
    private List<String> areas;
    private List<String> cargos;
    private List<String> universidades;
    private List<String> carreras;

    // Para capacitacion
    private List<String> especialidadesCapacitacion;
    private java.math.BigDecimal horasMinimasCapacitacion;
    private java.math.BigDecimal horasMaximasCapacitacion;

    // Para experiencia
    private List<String> especialidadesExperiencia;
    private Integer anosMinimosExperiencia;
    private Integer anosMaximosExperiencia;
}