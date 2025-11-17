package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codPersona;

    // Campos básicos
    private String tipPersona;
    private String desPersona;
    private String desCorta;
    private String descAlterna;
    private String desCortaAlt;
    private String vigente;
}