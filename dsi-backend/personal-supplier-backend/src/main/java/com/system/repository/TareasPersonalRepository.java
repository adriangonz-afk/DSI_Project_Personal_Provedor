package com.system.repository;

import com.system.entity.TareasPersonal;
import com.system.entity.id.TareasPersonalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareasPersonalRepository extends JpaRepository<TareasPersonal, TareasPersonalId> {
    // Método para encontrar tareas por empleado
    List<TareasPersonal> findByIdCodCiaAndIdCodEmpleado(Long codCia, Long codEmpleado);
    
    // Método para encontrar tareas por proyecto
    List<TareasPersonal> findByIdCodCiaAndIdCodPyto(Long codCia, Long codPyto);
    
    // Método para encontrar tareas por empleado y proyecto
    List<TareasPersonal> findByIdCodCiaAndIdCodEmpleadoAndIdCodPyto(Long codCia, Long codEmpleado, Long codPyto);
}