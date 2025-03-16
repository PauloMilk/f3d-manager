package br.com.paulocosta.f3dmanager.entities

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
@Entity
@Table(name = "tb_filament")
data class Filament(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_filament")
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "cd_filament_brand", nullable = false)
    val brand: FilamentBrand,

    @ManyToOne
    @JoinColumn(name = "cd_filament_type", nullable = false)
    val type: FilamentType,

    @ManyToOne
    @JoinColumn(name = "cd_filament_color", nullable = false)
    val color: FilamentColor,

    @Column(name = "vl_total_weight", nullable = false)
    val totalWeight: Int,  // Weight in grams

    @Column(name = "vl_remaining_weight", nullable = false)
    var remainingWeight: Int,  // Updated when consuming filament

    @Column(name = "vl_cost", nullable = true)
    val cost: BigDecimal? = null,

    @Column(name = "dt_purchase")
    val purchaseDate: LocalDate? = null
)