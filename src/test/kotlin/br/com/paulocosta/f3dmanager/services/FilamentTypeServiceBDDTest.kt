package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.config.TestContainerConfig
import br.com.paulocosta.f3dmanager.entities.FilamentType
import br.com.paulocosta.f3dmanager.repositories.FilamentTypeRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ContextConfiguration(initializers = [TestContainerConfig::class])
@Transactional
class FilamentTypeServiceBDDTest {

    @Autowired
    private lateinit var filamentTypeService: FilamentTypeService

    @Autowired
    private lateinit var filamentTypeRepository: FilamentTypeRepository

    @Test
    fun `Given a new filament type When it is created Then it should be retrievable`() {
        // Given
        val type = FilamentType(name = "PLA")

        // When
        val savedType = filamentTypeService.createType(type)

        // Then
        val retrievedType = filamentTypeRepository.findById(savedType.id)
        assertThat(retrievedType).isPresent
        assertThat(retrievedType.get().name).isEqualTo("PLA")
    }

    @Test
    fun `Given multiple filament types When retrieving all Then should return the correct list`() {
        // Given
        filamentTypeService.createType(FilamentType(name = "PLA"))
        filamentTypeService.createType(FilamentType(name = "PETG"))

        // When
        val types = filamentTypeService.getAllTypes()

        // Then
        assertThat(types).hasSize(2)
        assertThat(types.map { it.name }).containsExactlyInAnyOrder("PLA", "PETG")
    }

    @Test
    fun `Given an existing filament type When creating a duplicate Then it should throw a unique constraint exception`() {
        // Given: Criamos um tipo de filamento no banco
        filamentTypeService.createType(FilamentType(name = "PLA"))

        // When: Tentamos criar o mesmo tipo novamente
        val exception = org.junit.jupiter.api.assertThrows<org.springframework.dao.DataIntegrityViolationException> {
            filamentTypeService.createType(FilamentType(name = "PLA"))
        }

        // Then: Deve lançar uma exceção indicando violação de UNIQUE constraint
        assertThat(exception.message).contains("constraint")
    }
}