package com.system.repository;

import com.system.entity.CargosAreas;
import com.system.entity.id.CargosAreasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargosAreasRepository extends JpaRepository<CargosAreas, CargosAreasId> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
