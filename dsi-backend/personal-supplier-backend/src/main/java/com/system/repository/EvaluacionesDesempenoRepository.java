package com.system.repository;

import com.system.entity.EvaluacionesDesempeno;
import com.system.entity.id.EvaluacionesDesempenoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionesDesempenoRepository extends JpaRepository<EvaluacionesDesempeno, EvaluacionesDesempenoId> {
    // Método para encontrar evaluaciones de desempeño por empleado
    List<EvaluacionesDesempeno> findByIdCodCiaAndIdCodEmpleado(Long codCia, Long codEmpleado);
}
