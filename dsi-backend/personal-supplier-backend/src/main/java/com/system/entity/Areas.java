package com.system.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "AREAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Areas {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codArea", column = @Column(name = "CODAREA"))
    })
    private com.system.entity.id.AreasId id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;

    @Column(name = "ESTADO", nullable = false, length = 1, columnDefinition = "CHAR(1)")
    @Builder.Default
    private String estado = "A";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false)
    private Cia cia;
}
