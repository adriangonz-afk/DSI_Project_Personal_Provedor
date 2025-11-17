package com.system.repository;

import com.system.entity.GradosAcademicos;
import com.system.entity.id.GradosAcademicosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradosAcademicosRepository extends JpaRepository<GradosAcademicos, GradosAcademicosId> {
    // Método para encontrar grados académicos por empleado
    List<GradosAcademicos> findByIdCodCiaAndIdCodEmpleado(Long codCia, Long codEmpleado);
    
    // Métodos para dashboard
    @Query("SELECT ga.tipoGrado, COUNT(ga) FROM GradosAcademicos ga GROUP BY ga.tipoGrado")
    List<Object[]> countGradosPorTipo();
}
