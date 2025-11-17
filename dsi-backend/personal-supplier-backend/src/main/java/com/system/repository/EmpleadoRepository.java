package com.system.repository;

import com.system.entity.Empleado;
import com.system.entity.id.EmpleadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, EmpleadoId> {
    // Trae todos los empleados con la entidad persona asociada (fetch join)
    @Query("SELECT e FROM Empleado e LEFT JOIN FETCH e.persona")
    List<Empleado> findAllWithPersona();
    
    // Métodos para dashboard
       Long countByVigente(String vigente); // Usar "1" para vigente, "0" para no vigente
    
    @Query("SELECT a.nombre, COUNT(e) FROM Empleado e " +
           "JOIN CargosAreas ca ON e.codCargo = ca.id.codCargo AND e.id.codCia = ca.id.codCia " +
           "JOIN Areas a ON ca.id.codArea = a.id.codArea AND ca.id.codCia = a.id.codCia " +
           "WHERE e.vigente = '1' " +
           "GROUP BY a.nombre")
    List<Object[]> countEmpleadosPorArea();
    
    @Query("SELECT ca.nombreCargo, COUNT(e) FROM Empleado e " +
           "JOIN CargosAreas ca ON e.codCargo = ca.id.codCargo AND e.id.codCia = ca.id.codCia " +
           "WHERE e.vigente = '1' " +
           "GROUP BY ca.nombreCargo")
    List<Object[]> countEmpleadosPorCargo();
}
