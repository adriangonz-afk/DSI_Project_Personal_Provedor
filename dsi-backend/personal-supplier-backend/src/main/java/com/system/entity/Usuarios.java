package com.system.entity;

import lombok.*;
import jakarta.persistence.*;
import com.system.entity.id.UsuariosId;

@Entity
@Table(name = "USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuarios {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "codCia", column = @Column(name = "CODCIA")),
        @AttributeOverride(name = "codUsuario", column = @Column(name = "CODUSUARIO"))
    })
    private UsuariosId id;

    @Column(name = "USERNAME", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "PASSWORD_HASH", nullable = false, length = 200)
    private String passwordHash;

    @Column(name = "TIPO_USUARIO", nullable = false, length = 15)
    private String tipoUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODCIA", referencedColumnName = "CODCIA", insertable = false, updatable = false)
    private Cia cia;
}
