package br.com.paulocosta.f3dmanager.entities

import jakarta.persistence.*

@Entity
@Table(name = "tb_filament_color")
data class FilamentColor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_filament_color")
    val id: Int = 0,

    @Column(unique = true, nullable = false, name = "nm_color")
    val name: String
)