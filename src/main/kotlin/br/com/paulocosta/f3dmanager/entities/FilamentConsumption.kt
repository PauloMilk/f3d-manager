package br.com.paulocosta.f3dmanager.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tb_filament_consumption")
data class FilamentConsumption(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_filament_consumption")
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "cd_filament")
    val filament: Filament,

    @Column(name = "vl_consumed_amount")
    val consumedAmount: Int,  // In grams
    @Column(name = "dt_consumption")
    val consumptionDate: LocalDateTime = LocalDateTime.now()
) {
}