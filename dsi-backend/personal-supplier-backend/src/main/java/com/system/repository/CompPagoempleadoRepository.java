package com.system.repository;

import com.system.entity.CompPagoempleado;
import com.system.entity.id.CompPagoempleadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompPagoempleadoRepository extends JpaRepository<CompPagoempleado, CompPagoempleadoId> {
    // Método para encontrar comprobantes de pago por empleado
    List<CompPagoempleado> findByIdCodCiaAndIdCodEmpleado(Long codCia, Long codEmpleado);
}
