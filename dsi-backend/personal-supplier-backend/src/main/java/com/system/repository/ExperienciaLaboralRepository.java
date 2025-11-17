package com.system.repository;

import com.system.entity.ExperienciaLaboral;
import com.system.entity.id.ExperienciaLaboralId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, ExperienciaLaboralId> {
    // Método para encontrar experiencia laboral por empleado
    List<ExperienciaLaboral> findByIdCodCiaAndIdCodEmpleado(Long codCia, Long codEmpleado);
    
    // Métodos para dashboard
    @Query("SELECT AVG(CASE WHEN el.fechaFin IS NOT NULL THEN " +
           "YEAR(el.fechaFin) - YEAR(el.fechaInicio) " +
           "ELSE YEAR(CURRENT_DATE) - YEAR(el.fechaInicio) END) " +
           "FROM ExperienciaLaboral el")
    Double getExperienciaPromedio();
}
