package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.entities.FilamentType
import br.com.paulocosta.f3dmanager.repositories.FilamentTypeRepository
import org.springframework.stereotype.Service

@Service
class FilamentTypeService(private val filamentTypeRepository: FilamentTypeRepository) {
    fun getAllTypes(): List<FilamentType> = filamentTypeRepository.findAll()

    fun createType(type: FilamentType): FilamentType =
        filamentTypeRepository.save(type)
}