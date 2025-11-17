package com.system.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "CIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cia {
    @Id
    @Column(name = "CODCIA", nullable = false)
    private Long codCia;

    @Column(name = "DESCIA", nullable = false, length = 100)
    private String desCia;

    @Column(name = "DESCORTA", nullable = false, length = 20)
    private String desCorta;

    @Column(name = "VIGENTE", nullable = false, length = 1)
    private String vigente;
}
