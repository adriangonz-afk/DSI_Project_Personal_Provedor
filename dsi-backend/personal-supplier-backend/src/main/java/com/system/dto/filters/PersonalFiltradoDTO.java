package com.system.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.system.dto.PersonaDTO;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalFiltradoDTO {
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

    // Información de proyectos actuales
    private List<String> proyectosActuales;

    // Información de áreas y cargos actuales
    private List<String> areasActuales;
    private List<String> cargosActuales;

    // Información académica
    private List<String> universidades;
    private List<String> carreras;

    // Especialidades y capacitaciones
    private List<String> especialidadesCapacitacion;
    private List<String> especialidadesExperiencia;
}