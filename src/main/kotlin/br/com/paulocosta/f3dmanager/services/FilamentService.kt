package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.entities.Filament
import br.com.paulocosta.f3dmanager.entities.FilamentConsumption
import br.com.paulocosta.f3dmanager.repositories.FilamentConsumptionRepository
import br.com.paulocosta.f3dmanager.repositories.FilamentRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


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
    fun registerConsumption(filamentId: Int, amount: Int) {
        val filament = getFilamentById(filamentId)

        if (filament.remainingWeight < amount) {
            throw RuntimeException("Not enough filament available")
        }

        // Atualiza peso restante
        filament.remainingWeight -= amount
        filamentRepository.save(filament)

        // Registra no histórico de consumo
        val consumption = FilamentConsumption(
            filament = filament,
            consumedAmount = amount
        )
        filamentConsumptionRepository.save(consumption)
    }
}