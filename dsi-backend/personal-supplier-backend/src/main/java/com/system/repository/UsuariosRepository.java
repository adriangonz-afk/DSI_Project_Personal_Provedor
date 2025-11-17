package com.system.repository;

import com.system.entity.Usuarios;
import com.system.entity.id.UsuariosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, UsuariosId> {
    // Puedes agregar métodos personalizados aquí si es necesario
}