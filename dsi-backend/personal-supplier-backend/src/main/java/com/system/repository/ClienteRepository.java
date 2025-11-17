package com.system.repository;

import com.system.entity.Cliente;
import com.system.entity.id.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
