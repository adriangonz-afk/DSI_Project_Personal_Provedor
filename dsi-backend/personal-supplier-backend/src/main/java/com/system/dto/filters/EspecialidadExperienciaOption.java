package com.system.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadExperienciaOption {
    private String especialidad;
    private Integer anosMinimos;
    private Integer anosMaximos;
}