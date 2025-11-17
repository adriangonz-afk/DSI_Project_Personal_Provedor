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
public class ContratosProveedorDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codContrato;
    private Long codProveedor;
    private Long codPyto;

    // Campos básicos
    private String numeroContrato;
    private String tipoContrato;
    private Date fechaInicio; // LocalDate -> Date
    private Date fechaFin; // LocalDate -> Date
    private Double montoTotal; // BigDecimal -> Double
    private String moneda;
    private byte[] documentoContrato;
}