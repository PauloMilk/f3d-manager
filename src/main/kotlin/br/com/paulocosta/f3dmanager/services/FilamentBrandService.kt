package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.entities.FilamentBrand
import br.com.paulocosta.f3dmanager.repositories.FilamentBrandRepository
import org.springframework.stereotype.Service

@Service
class FilamentBrandService(private val filamentBrandRepository: FilamentBrandRepository) {
    fun getAllBrands(): List<FilamentBrand> = filamentBrandRepository.findAll()

    fun createBrand(brand: FilamentBrand): FilamentBrand {
        if (filamentBrandRepository.existsByName(brand.name)) {
            throw IllegalArgumentException("Brand already exists!")
        }
        return filamentBrandRepository.save(brand)
    }

    fun deleteBrand(id: Int) {
        if (!filamentBrandRepository.existsById(id)) {
            throw RuntimeException("Brand not found!")
        }
        filamentBrandRepository.deleteById(id)
    }

    fun getBrandById(id: Int): FilamentBrand {
        return filamentBrandRepository.findById(id).orElseThrow {
            throw RuntimeException("Brand not found with id: $id")
        }
    }

}