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
public class EspecialidadCapacitacionOption {
    private String especialidad;
    private BigDecimal horasMinimas;
    private BigDecimal horasMaximas;
}