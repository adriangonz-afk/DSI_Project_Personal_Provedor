package com.system.repository;

import com.system.entity.Areas;
import com.system.entity.id.AreasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreasRepository extends JpaRepository<Areas, AreasId> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
