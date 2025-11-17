package com.system.entity;

import com.system.entity.id.CargosAreasId;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "CARGOS_AREAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargosAreas {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCargo", column = @Column(name = "CODCARGO")),
        @AttributeOverride(name = "codArea", column = @Column(name = "CODAREA")),
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA"))
    })
    private CargosAreasId id;

    @Column(name = "NOMBRE_CARGO", nullable = false, length = 100)
    private String nombreCargo;

    // Relación ManyToOne con Areas (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODAREA", referencedColumnName = "CODAREA", insertable = false, updatable = false)
    })
    private Areas areas;
}
