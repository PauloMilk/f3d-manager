package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.config.TestContainerConfig
import br.com.paulocosta.f3dmanager.entities.FilamentBrand
import br.com.paulocosta.f3dmanager.repositories.FilamentBrandRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ContextConfiguration(initializers = [TestContainerConfig::class])
@Transactional
class FilamentBrandServiceBDDTest {

    @Autowired
    private lateinit var filamentBrandService: FilamentBrandService

    @Autowired
    private lateinit var filamentBrandRepository: FilamentBrandRepository

    @Test
    fun `Given a new brand When it is created Then it should be retrievable`() {
        // Given
        val brand = FilamentBrand(name = "Prusa")

        // When
        val savedBrand = filamentBrandService.createBrand(brand)

        // Then
        val retrievedBrand = filamentBrandRepository.findById(savedBrand.id)
        assertThat(retrievedBrand).isPresent
        assertThat(retrievedBrand.get().name).isEqualTo("Prusa")
    }

    @Test
    fun `Given multiple brands When retrieving all Then should return the correct list`() {
        // Given
        filamentBrandService.createBrand(FilamentBrand(name = "Prusa"))
        filamentBrandService.createBrand(FilamentBrand(name = "eSun"))

        // When
        val brands = filamentBrandService.getAllBrands()

        // Then
        assertThat(brands).hasSize(2)
        assertThat(brands.map { it.name }).containsExactlyInAnyOrder("Prusa", "eSun")
    }

    @Test
    fun `Given an existing brand When creating a duplicate Then it should throw a unique constraint exception`() {
        // Given: Criamos uma marca no banco
        filamentBrandService.createBrand(FilamentBrand(name = "Prusa"))

        // When: Tentamos criar a mesma marca novamente
        val exception = org.junit.jupiter.api.assertThrows<org.springframework.dao.DataIntegrityViolationException> {
            filamentBrandService.createBrand(FilamentBrand(name = "Prusa"))
        }

        // Then: Deve lançar uma exceção indicando violação de UNIQUE constraint
        assertThat(exception.message).contains("constraint")
    }
}