package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentosProveedorDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codDocumento;
    private Long codProveedor;

    // Campos básicos
    private String tipoDocumento;
    private String numeroDocumento;
    private byte[] archivo;
    private String tipoArchivo;
    private Date fechaEmision; // LocalDate -> Date
    private Date fechaVencimiento; // LocalDate -> Date
}