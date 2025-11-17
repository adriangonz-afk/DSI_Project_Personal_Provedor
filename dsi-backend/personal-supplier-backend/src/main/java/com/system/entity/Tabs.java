package com.system.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "TABS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tabs {
    @Id
    @Column(name = "CODTAB", nullable = false, length = 3)
    private String codTab;

    @Column(name = "DENTAB", nullable = false, length = 50)
    private String denTab;

    @Column(name = "DENCORTA", nullable = false, length = 10)
    private String denCorta;

    @Column(name = "VIGENTE", nullable = false, length = 1)
    private String vigente;
}
