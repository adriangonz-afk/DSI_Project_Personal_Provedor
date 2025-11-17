package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompPagoempleadoDTO {
    // Clave compuesta (plano)
    private Long codCia;
    private Long codEmpleado;
    private String nroCP;

    // Campos básicos
    private Long codPyto;
    private Integer nroPago;
    private String tCompPago;
    private String eCompPago;
    private Date fecCP; // LocalDate -> Date
    private String tMoneda;
    private String eMoneda;
    private Double tipCambio; // BigDecimal -> Double
    private Double impMO; // BigDecimal -> Double
    private Double impNetoMN; // BigDecimal -> Double
    private Double impIGVMN; // BigDecimal -> Double
    private Double impTotalMn; // BigDecimal -> Double
    private String fotoCP;
    private String fotoAbono;
    private Date fecAbono; // LocalDate -> Date
    private String desAbono;
    private Integer semilla;
    private String tabEstado;
    private String codEstado;

@JsonProperty("tCompPago")
    public String getTCompPago() {
        return tCompPago;
    }
    @JsonProperty("eCompPago")
    public String getECompPago() {
        return eCompPago;
    }
    @JsonProperty("tMoneda")
    public String getTMoneda() {
        return tMoneda;
    }
    @JsonProperty("eMoneda")
    public String getEMoneda() {
        return eMoneda;
    }
}