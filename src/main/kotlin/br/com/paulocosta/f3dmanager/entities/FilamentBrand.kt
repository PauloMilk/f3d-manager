package br.com.paulocosta.f3dmanager.entities

import jakarta.persistence.*


@Entity
@Table(name = "tb_filament_brand")
data class FilamentBrand(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_filament_brand")
    val id: Int = 0,

    @Column(unique = true, nullable = false, name = "nm_brand")
    val name: String
)