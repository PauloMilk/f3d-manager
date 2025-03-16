package br.com.paulocosta.f3dmanager.entities

import jakarta.persistence.*

@Entity
@Table(name = "tb_filament_type")
data class FilamentType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_filament_type")
    val id: Int = 0,

    @Column(unique = true, nullable = false, name = "nm_type")
    val name: String
)