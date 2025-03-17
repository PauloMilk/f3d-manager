package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.entities.FilamentColor
import br.com.paulocosta.f3dmanager.repositories.FilamentColorRepository
import org.springframework.stereotype.Service

@Service
class FilamentColorService(private val filamentColorRepository: FilamentColorRepository) {
    fun getAllColors(): List<FilamentColor> = filamentColorRepository.findAll()

    fun getColorById(id: Int): FilamentColor {
        return filamentColorRepository.findById(id).orElseThrow {
            throw RuntimeException("Color not found with id: $id")
        }
    }

    fun createColor(color: FilamentColor) {
        if (filamentColorRepository.existsByName(color.name)) {
            throw IllegalArgumentException("Color already exists!")
        }
        filamentColorRepository.save(color)
    }

    fun deleteColor(id: Int) {
        if (!filamentColorRepository.existsById(id)) {
            throw RuntimeException("Color not found!")
        }
        filamentColorRepository.deleteById(id)
    }
}