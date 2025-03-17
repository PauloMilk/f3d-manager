package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.entities.FilamentType
import br.com.paulocosta.f3dmanager.repositories.FilamentTypeRepository
import org.springframework.stereotype.Service

@Service
class FilamentTypeService(private val filamentTypeRepository: FilamentTypeRepository) {
    fun getAllTypes(): List<FilamentType> = filamentTypeRepository.findAll()

    fun getTypeById(id: Int): FilamentType {
        return filamentTypeRepository.findById(id).orElseThrow {
            throw RuntimeException("Type not found with id: $id")
        }
    }

    fun createType(type: FilamentType): FilamentType {
        if (filamentTypeRepository.existsByName(type.name)) {
            throw IllegalArgumentException("Type already exists!")
        }
        return filamentTypeRepository.save(type)
    }

    fun deleteType(id: Int) {
        if (!filamentTypeRepository.existsById(id)) {
            throw RuntimeException("Type not found!")
        }
        filamentTypeRepository.deleteById(id)
    }
}