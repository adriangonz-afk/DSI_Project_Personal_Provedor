package com.system.repository;

import com.system.entity.Proyecto;
import com.system.entity.id.ProyectoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, ProyectoId> {
    // Métodos para dashboard
    Long countByVigente(String vigente); // Usar "1" para vigente, "0" para no vigente
    
}
