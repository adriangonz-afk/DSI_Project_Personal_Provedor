package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorCompletoDTO {
    // Información básica del proveedor
    private Long codCia;
    private Long codProveedor;
    private String nroRuc;
    private String vigente;
    
    // Información de la persona relacionada (si existe)
    private PersonaDTO persona;
    
    // Listas de relaciones
    private List<ContratosProveedorDTO> contratos;
    private List<DocumentosProveedorDTO> documentos;
    private List<ActividadesProveedorDTO> actividades;
    private List<CompPagocabDTO> comprobantePagos;
    private List<ServiciosProveedorDTO> servicios;
}