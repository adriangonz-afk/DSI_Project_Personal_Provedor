package com.system.repository;

import com.system.dto.filters.EspecialidadCapacitacionOption;
import com.system.dto.filters.EspecialidadExperienciaOption;
import com.system.dto.filters.PersonalFilterCriteria;
import com.system.entity.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonalFilterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> findAvailableProjects() {
        String jpql = "SELECT DISTINCT p.nombPyto FROM Proyecto p " +
                     "INNER JOIN PersonalProyectos pp ON pp.codPyto = p.id.codPyto AND pp.id.codCia = p.id.codCia " +
                     "WHERE p.nombPyto IS NOT NULL " +
                     "ORDER BY p.nombPyto";
        
        Query query = entityManager.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findAvailableAreas() {
        String jpql = "SELECT DISTINCT a.nombre FROM Areas a " +
                     "INNER JOIN PersonalProyectos pp ON pp.codArea = a.id.codArea AND pp.id.codCia = a.id.codCia " +
                     "WHERE a.nombre IS NOT NULL " +
                     "ORDER BY a.nombre";
        
        Query query = entityManager.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findAvailableCargos() {
        String jpql = "SELECT DISTINCT ca.nombreCargo FROM CargosAreas ca " +
                     "INNER JOIN PersonalProyectos pp ON pp.codCargo = ca.id.codCargo AND pp.codArea = ca.id.codArea AND pp.id.codCia = ca.id.codCia " +
                     "WHERE ca.nombreCargo IS NOT NULL " +
                     "ORDER BY ca.nombreCargo";
        
        Query query = entityManager.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findAvailableUniversidades() {
        String jpql = "SELECT DISTINCT ga.institucion FROM GradosAcademicos ga " +
                     "WHERE ga.institucion IS NOT NULL " +
                     "ORDER BY ga.institucion";
        
        Query query = entityManager.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findAvailableCarreras() {
        String jpql = "SELECT DISTINCT ga.carrera FROM GradosAcademicos ga " +
                     "WHERE ga.carrera IS NOT NULL " +
                     "ORDER BY ga.carrera";
        
        Query query = entityManager.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<EspecialidadCapacitacionOption> findAvailableEspecialidadesCapacitacion() {
        String jpql = "SELECT ep.especialidad, MIN(ep.horasCapacitacion), MAX(ep.horasCapacitacion) " +
                     "FROM EspecialidadesPersonal ep " +
                     "WHERE ep.especialidad IS NOT NULL AND ep.horasCapacitacion IS NOT NULL " +
                     "GROUP BY ep.especialidad " +
                     "ORDER BY ep.especialidad";
        
        Query query = entityManager.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();
        
        return results.stream()
                .map(row -> EspecialidadCapacitacionOption.builder()
                        .especialidad((String) row[0])
                        .horasMinimas((BigDecimal) row[1])
                        .horasMaximas((BigDecimal) row[2])
                        .build())
                .collect(Collectors.toList());
    }

    public List<EspecialidadExperienciaOption> findAvailableEspecialidadesExperiencia() {
        String jpql = "SELECT el FROM ExperienciaLaboral el " +
                     "WHERE el.especialidad IS NOT NULL " +
                     "ORDER BY el.especialidad";
        
        Query query = entityManager.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<com.system.entity.ExperienciaLaboral> experiencias = query.getResultList();
        
        return experiencias.stream()
                .collect(Collectors.groupingBy(
                    com.system.entity.ExperienciaLaboral::getEspecialidad,
                    Collectors.mapping(exp -> {
                        LocalDate inicio = exp.getFechaInicio();
                        LocalDate fin = exp.getFechaFin() != null ? exp.getFechaFin() : LocalDate.now();
                        return Period.between(inicio, fin).getYears();
                    }, Collectors.toList())
                ))
                .entrySet().stream()
                .map(entry -> EspecialidadExperienciaOption.builder()
                        .especialidad(entry.getKey())
                        .anosMinimos(entry.getValue().stream().min(Integer::compareTo).orElse(0))
                        .anosMaximos(entry.getValue().stream().max(Integer::compareTo).orElse(0))
                        .build())
                .sorted((a, b) -> a.getEspecialidad().compareTo(b.getEspecialidad()))
                .collect(Collectors.toList());
    }

    public List<Empleado> findEmpleadosWithFilters(PersonalFilterCriteria criteria) {
        // Primero obtenemos los IDs únicos de los empleados que cumplen los criterios
        StringBuilder jpql = new StringBuilder(
            "SELECT DISTINCT e.id.codCia, e.id.codEmpleado FROM Empleado e " +
            "LEFT JOIN PersonalProyectos pp ON pp.id.codEmpleado = e.id.codEmpleado AND pp.id.codCia = e.id.codCia " +
            "LEFT JOIN Proyecto proy ON proy.id.codPyto = pp.codPyto AND proy.id.codCia = pp.id.codCia " +
            "LEFT JOIN Areas a ON a.id.codArea = pp.codArea AND a.id.codCia = pp.id.codCia " +
            "LEFT JOIN CargosAreas ca ON ca.id.codCargo = pp.codCargo AND ca.id.codArea = pp.codArea AND ca.id.codCia = pp.id.codCia " +
            "LEFT JOIN GradosAcademicos ga ON ga.id.codEmpleado = e.id.codEmpleado AND ga.id.codCia = e.id.codCia " +
            "LEFT JOIN EspecialidadesPersonal ep ON ep.id.codEmpleado = e.id.codEmpleado AND ep.id.codCia = e.id.codCia " +
            "LEFT JOIN ExperienciaLaboral el ON el.id.codEmpleado = e.id.codEmpleado AND el.id.codCia = e.id.codCia " +
            "WHERE 1=1"
        );

        List<Object> parameters = new ArrayList<>();
        int paramIndex = 1;

        if (criteria.getProyecto() != null && !criteria.getProyecto().trim().isEmpty()) {
            jpql.append(" AND proy.nombPyto = ?").append(paramIndex);
            parameters.add(criteria.getProyecto());
            paramIndex++;
        }

        if (criteria.getArea() != null && !criteria.getArea().trim().isEmpty()) {
            jpql.append(" AND a.nombre = ?").append(paramIndex);
            parameters.add(criteria.getArea());
            paramIndex++;
        }

        if (criteria.getCargo() != null && !criteria.getCargo().trim().isEmpty()) {
            jpql.append(" AND ca.nombreCargo = ?").append(paramIndex);
            parameters.add(criteria.getCargo());
            paramIndex++;
        }

        if (criteria.getUniversidad() != null && !criteria.getUniversidad().trim().isEmpty()) {
            jpql.append(" AND ga.institucion = ?").append(paramIndex);
            parameters.add(criteria.getUniversidad());
            paramIndex++;
        }

        if (criteria.getCarrera() != null && !criteria.getCarrera().trim().isEmpty()) {
            jpql.append(" AND ga.carrera = ?").append(paramIndex);
            parameters.add(criteria.getCarrera());
            paramIndex++;
        }

        if (criteria.getEspecialidadCapacitacion() != null && !criteria.getEspecialidadCapacitacion().trim().isEmpty()) {
            jpql.append(" AND ep.especialidad = ?").append(paramIndex);
            parameters.add(criteria.getEspecialidadCapacitacion());
            paramIndex++;

            if (criteria.getHorasMinimas() != null) {
                jpql.append(" AND ep.horasCapacitacion >= ?").append(paramIndex);
                parameters.add(criteria.getHorasMinimas());
                paramIndex++;
            }

            if (criteria.getHorasMaximas() != null) {
                jpql.append(" AND ep.horasCapacitacion <= ?").append(paramIndex);
                parameters.add(criteria.getHorasMaximas());
                paramIndex++;
            }
        }

        if (criteria.getEspecialidadExperiencia() != null && !criteria.getEspecialidadExperiencia().trim().isEmpty()) {
            jpql.append(" AND el.especialidad = ?").append(paramIndex);
            parameters.add(criteria.getEspecialidadExperiencia());
            paramIndex++;

            // Para filtros de experiencia por años, necesitamos una subconsulta más compleja
            if (criteria.getAnosMinimos() != null || criteria.getAnosMaximos() != null) {
                jpql.append(" AND e.id IN (")
                    .append("SELECT DISTINCT el2.empleado.id FROM ExperienciaLaboral el2 ")
                    .append("WHERE el2.especialidad = ?").append(paramIndex - 1);
                
                if (criteria.getAnosMinimos() != null) {
                    jpql.append(" AND FUNCTION('MONTHS_BETWEEN', COALESCE(el2.fechaFin, CURRENT_DATE), el2.fechaInicio) / 12 >= ?").append(paramIndex);
                    parameters.add(criteria.getAnosMinimos().doubleValue());
                    paramIndex++;
                }
                
                if (criteria.getAnosMaximos() != null) {
                    jpql.append(" AND FUNCTION('MONTHS_BETWEEN', COALESCE(el2.fechaFin, CURRENT_DATE), el2.fechaInicio) / 12 <= ?").append(paramIndex);
                    parameters.add(criteria.getAnosMaximos().doubleValue());
                    paramIndex++;
                }
                
                jpql.append(")");
            }
        }

        // Cambiar el ORDER BY para evitar el error ORA-01791
        jpql.append(" ORDER BY e.id.codEmpleado");

        Query query = entityManager.createQuery(jpql.toString());
        for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i + 1, parameters.get(i));
        }

        @SuppressWarnings("unchecked")
        List<Object[]> idResults = query.getResultList();
        
        // Si no hay resultados, devolver lista vacía
        if (idResults.isEmpty()) {
            return new ArrayList<>();
        }
        
        // Crear empleados manualmente para evitar cargar el BLOB
        List<Empleado> results = new ArrayList<>();
        for (Object[] row : idResults) {
            Long codCia = (Long) row[0];
            Long codEmpleado = (Long) row[1];

            // Consulta específica sin BLOB, pero ahora también traemos la persona asociada
            String empleadoJpql = "SELECT e FROM Empleado e LEFT JOIN FETCH e.persona WHERE e.id.codCia = :codCia AND e.id.codEmpleado = :codEmpleado";
            Query empleadoQuery = entityManager.createQuery(empleadoJpql);
            empleadoQuery.setParameter("codCia", codCia);
            empleadoQuery.setParameter("codEmpleado", codEmpleado);
            Empleado empleado = (Empleado) empleadoQuery.getSingleResult();
            // No establecer foto (dejarla null para evitar el BLOB)
            empleado.setFoto(null);
            results.add(empleado);
        }
        return results;
    }

    public List<String> findProyectosByEmpleado(Long codCia, Long codEmpleado) {
        String jpql = "SELECT DISTINCT p.nombPyto FROM PersonalProyectos pp " +
                     "INNER JOIN pp.proyecto p " +
                     "WHERE pp.id.codCia = :codCia AND pp.id.codEmpleado = :codEmpleado " +
                     "AND (pp.fechaDesasignacion IS NULL OR pp.fechaDesasignacion > CURRENT_DATE) " +
                     "ORDER BY p.nombPyto";
        
        Query query = entityManager.createQuery(jpql);
        query.setParameter("codCia", codCia);
        query.setParameter("codEmpleado", codEmpleado);
        
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findAreasByEmpleado(Long codCia, Long codEmpleado) {
        String jpql = "SELECT DISTINCT a.nombre FROM PersonalProyectos pp " +
                     "INNER JOIN pp.areas a " +
                     "WHERE pp.id.codCia = :codCia AND pp.id.codEmpleado = :codEmpleado " +
                     "AND (pp.fechaDesasignacion IS NULL OR pp.fechaDesasignacion > CURRENT_DATE) " +
                     "ORDER BY a.nombre";
        
        Query query = entityManager.createQuery(jpql);
        query.setParameter("codCia", codCia);
        query.setParameter("codEmpleado", codEmpleado);
        
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findCargosByEmpleado(Long codCia, Long codEmpleado) {
        String jpql = "SELECT DISTINCT ca.nombreCargo FROM PersonalProyectos pp " +
                     "INNER JOIN pp.cargosAreas ca " +
                     "WHERE pp.id.codCia = :codCia AND pp.id.codEmpleado = :codEmpleado " +
                     "AND (pp.fechaDesasignacion IS NULL OR pp.fechaDesasignacion > CURRENT_DATE) " +
                     "ORDER BY ca.nombreCargo";
        
        Query query = entityManager.createQuery(jpql);
        query.setParameter("codCia", codCia);
        query.setParameter("codEmpleado", codEmpleado);
        
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findUniversidadesByEmpleado(Long codCia, Long codEmpleado) {
        String jpql = "SELECT DISTINCT ga.institucion FROM GradosAcademicos ga " +
                     "WHERE ga.id.codCia = :codCia AND ga.id.codEmpleado = :codEmpleado " +
                     "AND ga.institucion IS NOT NULL " +
                     "ORDER BY ga.institucion";
        
        Query query = entityManager.createQuery(jpql);
        query.setParameter("codCia", codCia);
        query.setParameter("codEmpleado", codEmpleado);
        
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findCarrerasByEmpleado(Long codCia, Long codEmpleado) {
        String jpql = "SELECT DISTINCT ga.carrera FROM GradosAcademicos ga " +
                     "WHERE ga.id.codCia = :codCia AND ga.id.codEmpleado = :codEmpleado " +
                     "AND ga.carrera IS NOT NULL " +
                     "ORDER BY ga.carrera";
        
        Query query = entityManager.createQuery(jpql);
        query.setParameter("codCia", codCia);
        query.setParameter("codEmpleado", codEmpleado);
        
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findEspecialidadesCapacitacionByEmpleado(Long codCia, Long codEmpleado) {
        String jpql = "SELECT DISTINCT ep.especialidad FROM EspecialidadesPersonal ep " +
                     "WHERE ep.id.codCia = :codCia AND ep.id.codEmpleado = :codEmpleado " +
                     "AND ep.especialidad IS NOT NULL " +
                     "ORDER BY ep.especialidad";
        
        Query query = entityManager.createQuery(jpql);
        query.setParameter("codCia", codCia);
        query.setParameter("codEmpleado", codEmpleado);
        
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }

    public List<String> findEspecialidadesExperienciaByEmpleado(Long codCia, Long codEmpleado) {
        String jpql = "SELECT DISTINCT el.especialidad FROM ExperienciaLaboral el " +
                     "WHERE el.id.codCia = :codCia AND el.id.codEmpleado = :codEmpleado " +
                     "AND el.especialidad IS NOT NULL " +
                     "ORDER BY el.especialidad";
        
        Query query = entityManager.createQuery(jpql);
        query.setParameter("codCia", codCia);
        query.setParameter("codEmpleado", codEmpleado);
        
        @SuppressWarnings("unchecked")
        List<String> results = query.getResultList();
        return results;
    }
}