package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.entities.Filament
import br.com.paulocosta.f3dmanager.entities.FilamentConsumption
import br.com.paulocosta.f3dmanager.repositories.FilamentConsumptionRepository
import br.com.paulocosta.f3dmanager.repositories.FilamentRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class FilamentService(
    private val filamentRepository: FilamentRepository,
    private val filamentConsumptionRepository: FilamentConsumptionRepository
) {
    fun getAllFilaments(): List<Filament> = filamentRepository.findAll()

    fun getFilamentById(id: Int): Filament =
        filamentRepository.findById(id).orElseThrow { RuntimeException("Filament not found") }

    fun createFilament(filament: Filament): Filament =
        filamentRepository.save(filament)

    fun deleteFilament(id: Int) {
        val filament = getFilamentById(id)
        filamentRepository.delete(filament)
    }

    @Transactional
    fun registerConsumption(id: Int, consumedAmount: Int) {
        val filament = filamentRepository.findById(id).orElseThrow {
            throw RuntimeException("Filament not found!")
        }

        if (consumedAmount <= 0) {
            throw IllegalArgumentException("Consumed amount must be greater than zero!")
        }

        if (consumedAmount > filament.remainingWeight) {
            throw IllegalArgumentException("Not enough filament available!")
        }

        filament.remainingWeight -= consumedAmount
        filamentRepository.save(filament)
    }

    fun updateFilament(filament: Filament) {
        if (!filamentRepository.existsById(filament.id)) {
            throw RuntimeException("Filament not found!")
        }
        filamentRepository.save(filament)
    }

    fun registerPurchase(id: Int, purchasedAmount: Int, cost: Double?) {
        val filament = filamentRepository.findById(id).orElseThrow {
            throw RuntimeException("Filament not found!")
        }

        if (purchasedAmount <= 0) {
            throw IllegalArgumentException("Purchased amount must be greater than zero!")
        }

        filament.totalWeight += purchasedAmount
        filament.remainingWeight += purchasedAmount

        if (cost != null) {
            filament.cost = BigDecimal.valueOf(cost)
        }

        filamentRepository.save(filament)
    }
}