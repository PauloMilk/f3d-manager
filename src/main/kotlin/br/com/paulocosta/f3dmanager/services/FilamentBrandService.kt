package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.entities.FilamentBrand
import br.com.paulocosta.f3dmanager.repositories.FilamentBrandRepository
import org.springframework.stereotype.Service

@Service
class FilamentBrandService(private val filamentBrandRepository: FilamentBrandRepository) {
    fun getAllBrands(): List<FilamentBrand> = filamentBrandRepository.findAll()

    fun createBrand(brand: FilamentBrand): FilamentBrand =
        filamentBrandRepository.save(brand)
}