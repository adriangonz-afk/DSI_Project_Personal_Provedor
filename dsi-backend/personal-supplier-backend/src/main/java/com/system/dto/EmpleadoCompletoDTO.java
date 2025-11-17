package com.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoCompletoDTO {
    // Información básica del empleado (clave compuesta plana)
    private Long codCia;
    private Long codEmpleado;
    
    // Campos básicos del empleado
    private String direcc;
    private String celular;
    private String hobby;
    private byte[] foto;
    private Date fecNac;
    private String dni;
    private String nroCIP;
    private Date fecCIPVig;
    private String licCond;
    private String flgEmplIEA;
    private String observac;
    private Integer codCargo;
    private String email;
    private String vigente;
    
    // Información de la persona relacionada
    private PersonaDTO persona;
    
    // Listas de entidades relacionadas
    private List<CompPagoempleadoDTO> comprobantePagos;
    private List<EspecialidadesPersonalDTO> especialidades;
    private List<EvaluacionesDesempenoDTO> evaluacionesDesempeno;
    private List<ExperienciaLaboralDTO> experienciasLaborales;
    private List<GradosAcademicosDTO> gradosAcademicos;
    private List<PersonalProyectosDTO> proyectos;
    private List<TareasPersonalDTO> tareas;
}