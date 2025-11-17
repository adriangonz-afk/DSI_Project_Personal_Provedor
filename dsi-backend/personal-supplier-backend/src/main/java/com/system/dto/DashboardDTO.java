package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {
    
    // Métricas generales
    private Long totalEmpleados;
    private Long totalProveedores;
    private Long totalProyectos;
    private Long totalClientes;
    private Long totalAreas;
    
    // Métricas de pagos
    private Long totalPagosRealizados;
    private BigDecimal montoTotalPagado;
    
    // Métricas de evaluaciones y formación
    private Long totalEvaluacionesDesempeno;
    private Long totalGradosAcademicos;
    private Long totalExperienciasLaborales;
    private Long totalEspecialidadesPersonal;
    
    // Estadísticas por estado/vigencia
    private Long empleadosVigentes;
    private Long empleadosNoVigentes;
    private Long proveedoresVigentes;
    private Long proveedoresNoVigentes;
    private Long proyectosActivos;
    private Long proyectosInactivos;
    
    // Distribuciones
    private Map<String, Long> empleadosPorArea;
    private Map<String, Long> empleadosPorCargo;
    private Map<String, Long> gradosAcademicosPorTipo;
    
    // Personal asignado a proyectos
    private Long personalAsignadoProyectos;
    private Long personalSinAsignar;
    
    // Métricas adicionales
    private Double experienciaLaboralPromedio; // En años
    private Long contratosProveedorActivos;
    private Long documentosProveedorRegistrados;
    private Long actividadesProveedorEnCurso;
}