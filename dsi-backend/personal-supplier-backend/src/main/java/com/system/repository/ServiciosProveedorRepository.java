package com.system.repository;

import com.system.entity.ServiciosProveedor;
import com.system.entity.id.ServiciosProveedorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiciosProveedorRepository extends JpaRepository<ServiciosProveedor, ServiciosProveedorId> {
    
    @Query("SELECT sp FROM ServiciosProveedor sp WHERE sp.id.codCia = :codCia AND sp.id.codProveedor = :codProveedor")
    List<ServiciosProveedor> findByProveedorId(@Param("codCia") Long codCia, @Param("codProveedor") Long codProveedor);
}
