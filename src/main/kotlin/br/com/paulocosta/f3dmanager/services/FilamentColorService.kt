package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.entities.FilamentColor
import br.com.paulocosta.f3dmanager.repositories.FilamentColorRepository
import org.springframework.stereotype.Service

@Service
class FilamentColorService(private val filamentColorRepository: FilamentColorRepository) {
    fun getAllColors(): List<FilamentColor> = filamentColorRepository.findAll()

    fun createColor(color: FilamentColor): FilamentColor =
        filamentColorRepository.save(color)
}