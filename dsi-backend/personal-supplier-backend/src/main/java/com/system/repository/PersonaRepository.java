package com.system.repository;

import com.system.entity.Persona;
import com.system.entity.id.PersonaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, PersonaId> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
