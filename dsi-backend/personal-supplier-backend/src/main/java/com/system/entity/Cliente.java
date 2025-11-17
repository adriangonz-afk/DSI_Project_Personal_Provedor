package com.system.entity;

import lombok.*;
import jakarta.persistence.*;
import com.system.entity.id.ClienteId;

@Entity
@Table(name = "CLIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @EmbeddedId
    private ClienteId id;

    @Column(name = "NRORUC", nullable = false, length = 20)
    private String nroRuc;

    @Column(name = "VIGENTE", nullable = false, length = 1)
    private String vigente;

    // Relación ManyToOne con Persona (clave compuesta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false),
        @JoinColumn(name = "CODCLIENTE", referencedColumnName = "CODPERSONA", insertable = false, updatable = false)
    })
    private Persona persona;
}
