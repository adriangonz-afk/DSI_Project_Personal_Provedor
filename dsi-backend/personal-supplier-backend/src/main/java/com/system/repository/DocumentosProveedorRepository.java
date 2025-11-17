package com.system.repository;

import com.system.entity.DocumentosProveedor;
import com.system.entity.id.DocumentosProveedorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentosProveedorRepository extends JpaRepository<DocumentosProveedor, DocumentosProveedorId> {
    
    @Query("SELECT dp FROM DocumentosProveedor dp WHERE dp.id.codCia = :codCia AND dp.id.codProveedor = :codProveedor")
    List<DocumentosProveedor> findByProveedorId(@Param("codCia") Long codCia, @Param("codProveedor") Long codProveedor);
}
