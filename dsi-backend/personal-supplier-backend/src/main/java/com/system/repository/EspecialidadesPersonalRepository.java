package com.system.repository;

import com.system.entity.EspecialidadesPersonal;
import com.system.entity.id.EspecialidadesPersonalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadesPersonalRepository extends JpaRepository<EspecialidadesPersonal, EspecialidadesPersonalId> {
    // Método para encontrar especialidades por empleado
    List<EspecialidadesPersonal> findByIdCodCiaAndIdCodEmpleado(Long codCia, Long codEmpleado);
}