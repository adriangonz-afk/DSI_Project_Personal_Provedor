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
public class ActividadesProveedorDTO {
    // Clave compuesta (plano)
    private Long codCIA;
    private Long codActividad;
    private Long codProveedor;
    private Long codPyto;
    private Long codContrato;

    // Campos básicos
    private String descripcion;
    private Date fechaActividad; // LocalDate -> Date
    private Double monto; // BigDecimal -> Double
    private String estado;
    private byte[] documento;
    private String observaciones;
}
