package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.config.TestContainerConfig
import br.com.paulocosta.f3dmanager.entities.FilamentColor
import br.com.paulocosta.f3dmanager.repositories.FilamentColorRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ContextConfiguration(initializers = [TestContainerConfig::class])
@Transactional
class FilamentColorServiceBDDTest {

    @Autowired
    private lateinit var filamentColorService: FilamentColorService

    @Autowired
    private lateinit var filamentColorRepository: FilamentColorRepository

    @Test
    fun `Given a new filament color When it is created Then it should be retrievable`() {
        // Given
        val color = FilamentColor(name = "Red")

        // When
        val savedColor = filamentColorService.createColor(color)

        // Then
        val retrievedColor = filamentColorRepository.findById(savedColor.id)
        assertThat(retrievedColor).isPresent
        assertThat(retrievedColor.get().name).isEqualTo("Red")
    }

    @Test
    fun `Given multiple filament colors When retrieving all Then should return the correct list`() {
        // Given
        filamentColorService.createColor(FilamentColor(name = "Red"))
        filamentColorService.createColor(FilamentColor(name = "Blue"))

        // When
        val colors = filamentColorService.getAllColors()

        // Then
        assertThat(colors).hasSize(2)
        assertThat(colors.map { it.name }).containsExactlyInAnyOrder("Red", "Blue")
    }

    @Test
    fun `Given an existing filament color When creating a duplicate Then it should throw a unique constraint exception`() {
        // Given: Criamos uma cor no banco
        filamentColorService.createColor(FilamentColor(name = "Red"))

        // When: Tentamos criar a mesma cor novamente
        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            filamentColorService.createColor(FilamentColor(name = "Red"))
        }

        // Then: Deve lançar uma exceção indicando violação de UNIQUE constraint
        assertThat(exception.message).contains("Color already exists!")
    }
}