package com.system.entity;

import lombok.*;
import jakarta.persistence.*;
import com.system.entity.id.PersonaId;

@Entity
@Table(name = "PERSONA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codPersona", column = @Column(name = "CODPERSONA"))
    })
    private PersonaId id;

    @Column(name = "TIPPERSONA", nullable = false, length = 1)
    private String tipPersona;

    @Column(name = "DESPERSONA", nullable = false, length = 100)
    private String desPersona;

    @Column(name = "DESCORTA", nullable = false, length = 30)
    private String desCorta;

    @Column(name = "DESCALTERNA", nullable = false, length = 100)
    private String descAlterna;

    @Column(name = "DESCORTAALT", nullable = false, length = 10)
    private String desCortaAlt;

    @Column(name = "VIGENTE", nullable = false, length = 1)
    private String vigente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false)
    private Cia cia;
}
