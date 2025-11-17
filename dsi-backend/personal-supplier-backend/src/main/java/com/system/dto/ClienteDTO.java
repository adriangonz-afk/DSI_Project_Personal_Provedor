package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    // Clave compuesta
    private Long codCia;
    private Long codCliente;

    // Campos básicos
    private String nroRuc;
    private String vigente;
}
