package com.system.repository;

import com.system.entity.Proveedor;
import com.system.entity.id.ProveedorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, ProveedorId> {
    // Métodos para dashboard
    Long countByVigente(String vigente); // Usar "1" para vigente, "0" para no vigente
}
