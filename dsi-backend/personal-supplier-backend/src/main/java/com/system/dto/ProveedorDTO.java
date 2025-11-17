package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codProveedor;

    // Campos básicos
    private String nroRuc;
    private String vigente;
}