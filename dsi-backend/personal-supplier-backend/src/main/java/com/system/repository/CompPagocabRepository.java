package com.system.repository;

import com.system.entity.CompPagocab;
import com.system.entity.id.CompPagocabId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CompPagocabRepository extends JpaRepository<CompPagocab, CompPagocabId> {
    
    @Query("SELECT cp FROM CompPagocab cp WHERE cp.id.codCia = :codCia AND cp.id.codProveedor = :codProveedor")
    List<CompPagocab> findByProveedorId(@Param("codCia") Long codCia, @Param("codProveedor") Long codProveedor);
    
    // Métodos para dashboard
    @Query("SELECT COALESCE(SUM(cp.impTotalMn), 0) FROM CompPagocab cp")
    BigDecimal sumMontoTotal();
}
