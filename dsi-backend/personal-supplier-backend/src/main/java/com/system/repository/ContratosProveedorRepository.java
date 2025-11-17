package com.system.repository;

import com.system.entity.ContratosProveedor;
import com.system.entity.id.ContratosProveedorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratosProveedorRepository extends JpaRepository<ContratosProveedor, ContratosProveedorId> {
    
    @Query("SELECT cp FROM ContratosProveedor cp WHERE cp.id.codCia = :codCia AND cp.id.codProveedor = :codProveedor")
    List<ContratosProveedor> findByProveedorId(@Param("codCia") Long codCia, @Param("codProveedor") Long codProveedor);
    
    // Método para encontrar contratos por proyecto
    List<ContratosProveedor> findByIdCodCiaAndIdCodPyto(Long codCia, Long codPyto);
    
    // Método para encontrar contratos por proveedor y proyecto
    List<ContratosProveedor> findByIdCodCiaAndIdCodProveedorAndIdCodPyto(Long codCia, Long codProveedor, Long codPyto);
    
    // Métodos para dashboard
    @Query("SELECT COUNT(c) FROM ContratosProveedor c WHERE c.fechaFin IS NULL OR c.fechaFin > CURRENT_DATE")
    Long countContratosActivos();
}
