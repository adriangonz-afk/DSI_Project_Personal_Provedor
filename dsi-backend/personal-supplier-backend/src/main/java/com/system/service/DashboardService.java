package com.system.service;

import com.system.dto.DashboardDTO;
import com.system.repository.*;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class DashboardService {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private ProveedorRepository proveedorRepository;
    
    @Autowired
    private ProyectoRepository proyectoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private AreasRepository areasRepository;
    
    @Autowired
    private CompPagocabRepository compPagocabRepository;
    
    @Autowired
    private EvaluacionesDesempenoRepository evaluacionesDesempenoRepository;
    
    @Autowired
    private GradosAcademicosRepository gradosAcademicosRepository;
    
    @Autowired
    private ExperienciaLaboralRepository experienciaLaboralRepository;
    
    @Autowired
    private EspecialidadesPersonalRepository especialidadesPersonalRepository;
    
    @Autowired
    private PersonalProyectosRepository personalProyectosRepository;
    
    @Autowired
    private ContratosProveedorRepository contratosProveedorRepository;
    
    @Autowired
    private DocumentosProveedorRepository documentosProveedorRepository;
    
    @Autowired
    private ActividadesProveedorRepository actividadesProveedorRepository;
    
    public DashboardDTO getDashboardData() {
        try {
            log.info("Obteniendo datos para el dashboard");
            
            return DashboardDTO.builder()
                    // Métricas generales
                    .totalEmpleados(empleadoRepository.count())
                    .totalProveedores(proveedorRepository.count())
                    .totalProyectos(proyectoRepository.count())
                    .totalClientes(clienteRepository.count())
                    .totalAreas(areasRepository.count())
                    
                    // Métricas de pagos
                    .totalPagosRealizados(compPagocabRepository.count())
                    .montoTotalPagado(getMontoTotalPagado())
                    
                    // Métricas de evaluaciones y formación
                    .totalEvaluacionesDesempeno(evaluacionesDesempenoRepository.count())
                    .totalGradosAcademicos(gradosAcademicosRepository.count())
                    .totalExperienciasLaborales(experienciaLaboralRepository.count())
                    .totalEspecialidadesPersonal(especialidadesPersonalRepository.count())
                    
                    // Estadísticas por vigencia
                    .empleadosVigentes(empleadoRepository.countByVigente("1"))
                    .empleadosNoVigentes(empleadoRepository.countByVigente("0"))
                    .proveedoresVigentes(proveedorRepository.countByVigente("1"))
                    .proveedoresNoVigentes(proveedorRepository.countByVigente("0"))
                    .proyectosActivos(proyectoRepository.countByVigente("1"))
                    .proyectosInactivos(proyectoRepository.countByVigente("0"))
                    
                    // Distribuciones
                    .empleadosPorArea(getEmpleadosPorArea())
                    .empleadosPorCargo(getEmpleadosPorCargo())
                    .gradosAcademicosPorTipo(getGradosAcademicosPorTipo())
                    
                    // Personal asignado a proyectos
                    .personalAsignadoProyectos(personalProyectosRepository.countDistinctEmpleados())
                    .personalSinAsignar(getPersonalSinAsignar())
                    
                    // Métricas adicionales
                    .experienciaLaboralPromedio(getExperienciaLaboralPromedio())
                    .contratosProveedorActivos(contratosProveedorRepository.countContratosActivos())
                    .documentosProveedorRegistrados(documentosProveedorRepository.count())
                    .actividadesProveedorEnCurso(actividadesProveedorRepository.countByEstado("ACTIVO"))
                    .build();
                    
        } catch (Exception e) {
            log.error("Error al obtener datos del dashboard", e);
            throw new ServiceException("Error al obtener datos del dashboard", e);
        }
    }
    
    private BigDecimal getMontoTotalPagado() {
        try {
            return compPagocabRepository.sumMontoTotal();
        } catch (Exception e) {
            log.warn("Error al calcular monto total pagado", e);
            return BigDecimal.ZERO;
        }
    }
    
    private Map<String, Long> getEmpleadosPorArea() {
        try {
            List<Object[]> results = empleadoRepository.countEmpleadosPorArea();
            return results.stream()
                    .collect(Collectors.toMap(
                            result -> (String) result[0], // nombre del área
                            result -> ((Number) result[1]).longValue() // cantidad
                    ));
        } catch (Exception e) {
            log.warn("Error al obtener empleados por área", e);
            return new HashMap<>();
        }
    }
    
    private Map<String, Long> getEmpleadosPorCargo() {
        try {
            List<Object[]> results = empleadoRepository.countEmpleadosPorCargo();
            return results.stream()
                    .collect(Collectors.toMap(
                            result -> (String) result[0], // nombre del cargo
                            result -> ((Number) result[1]).longValue() // cantidad
                    ));
        } catch (Exception e) {
            log.warn("Error al obtener empleados por cargo", e);
            return new HashMap<>();
        }
    }
    
    
    private Map<String, Long> getGradosAcademicosPorTipo() {
        try {
            List<Object[]> results = gradosAcademicosRepository.countGradosPorTipo();
            return results.stream()
                    .collect(Collectors.toMap(
                            result -> (String) result[0], // tipo de grado
                            result -> ((Number) result[1]).longValue() // cantidad
                    ));
        } catch (Exception e) {
            log.warn("Error al obtener grados académicos por tipo", e);
            return new HashMap<>();
        }
    }
    
    private Long getPersonalSinAsignar() {
        try {
            Long totalEmpleados = empleadoRepository.countByVigente("1");
            Long personalAsignado = personalProyectosRepository.countDistinctEmpleados();
            return totalEmpleados - personalAsignado;
        } catch (Exception e) {
            log.warn("Error al calcular personal sin asignar", e);
            return 0L;
        }
    }
    
    private Double getExperienciaLaboralPromedio() {
        try {
            return experienciaLaboralRepository.getExperienciaPromedio();
        } catch (Exception e) {
            log.warn("Error al calcular experiencia laboral promedio", e);
            return 0.0;
        }
    }
}