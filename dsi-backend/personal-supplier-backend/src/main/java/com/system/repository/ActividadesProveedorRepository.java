package com.system.repository;

import com.system.entity.ActividadesProveedor;
import com.system.entity.id.ActividadesProveedorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadesProveedorRepository extends JpaRepository<ActividadesProveedor, ActividadesProveedorId> {
    
    @Query("SELECT ap FROM ActividadesProveedor ap WHERE ap.id.codCia = :codCia AND ap.id.codProveedor = :codProveedor")
    List<ActividadesProveedor> findByProveedorId(@Param("codCia") Long codCia, @Param("codProveedor") Long codProveedor);
    
    // Método para encontrar actividades por proyecto
    List<ActividadesProveedor> findByIdCodCiaAndIdCodPyto(Long codCia, Long codPyto);
    
    // Método para encontrar actividades por proveedor y proyecto
    List<ActividadesProveedor> findByIdCodCiaAndIdCodProveedorAndIdCodPyto(Long codCia, Long codProveedor, Long codPyto);
    
    // Métodos para dashboard
    Long countByEstado(String estado);
}
