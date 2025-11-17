package com.system.repository;

import com.system.entity.PersonalProyectos;
import com.system.entity.id.PersonalProyectosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalProyectosRepository extends JpaRepository<PersonalProyectos, PersonalProyectosId> {
    // Método para encontrar proyectos por empleado
    List<PersonalProyectos> findByIdCodCiaAndIdCodEmpleado(Long codCia, Long codEmpleado);
    
    // Método para encontrar empleados por proyecto
    List<PersonalProyectos> findByIdCodCiaAndCodPyto(Long codCia, Long codPyto);
    
    // Métodos para dashboard
    @Query("SELECT COUNT(DISTINCT pp.id.codEmpleado) FROM PersonalProyectos pp JOIN Empleado e ON pp.id.codEmpleado = e.id.codEmpleado AND pp.id.codCia = e.id.codCia WHERE e.vigente = '1'")
    Long countDistinctEmpleados();
}
