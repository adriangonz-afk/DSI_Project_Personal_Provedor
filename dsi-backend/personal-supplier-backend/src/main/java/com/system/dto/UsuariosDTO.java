package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosDTO {
    private Long codCia;
    private Long codUsuario;

    private String username;
    // private String passwordHash;
    private String tipoUsuario;
}
