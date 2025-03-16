package br.com.paulocosta.f3dmanager.services

import br.com.paulocosta.f3dmanager.config.TestContainerConfig
import br.com.paulocosta.f3dmanager.entities.Filament
import br.com.paulocosta.f3dmanager.entities.FilamentBrand
import br.com.paulocosta.f3dmanager.entities.FilamentColor
import br.com.paulocosta.f3dmanager.entities.FilamentType
import br.com.paulocosta.f3dmanager.repositories.FilamentBrandRepository
import br.com.paulocosta.f3dmanager.repositories.FilamentColorRepository
import br.com.paulocosta.f3dmanager.repositories.FilamentRepository
import br.com.paulocosta.f3dmanager.repositories.FilamentTypeRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
@ContextConfiguration(initializers = [TestContainerConfig::class])
@Transactional
class FilamentServiceBDDTest {

    @Autowired
    private lateinit var filamentService: FilamentService

    @Autowired
    private lateinit var filamentRepository: FilamentRepository

    @Autowired
    private lateinit var filamentBrandRepository: FilamentBrandRepository

    @Autowired
    private lateinit var filamentTypeRepository: FilamentTypeRepository

    @Autowired
    private lateinit var filamentColorRepository: FilamentColorRepository

    @Test
    fun `Given a new filament When it is created Then it should be retrievable`() {
        // Given: Um filamento novo cadastrado
        val filament = Filament(
            brand = mockBrand(),
            type = mockType(),
            color = mockColor(),
            totalWeight = 1000,
            remainingWeight = 1000,
            cost = BigDecimal(50.0),
            purchaseDate = LocalDate.now()
        )

        // When: Salvamos no banco
        val savedFilament = filamentService.createFilament(filament)

        // Then: O filamento deve ser recuperável
        val retrievedFilament = filamentService.getFilamentById(savedFilament.id)
        assertThat(retrievedFilament).isNotNull
        assertThat(retrievedFilament.id).isEqualTo(savedFilament.id)
        assertThat(retrievedFilament.remainingWeight).isEqualTo(1000)
    }

    @Test
    fun `Given an existing filament When consumption is registered Then the remaining weight should decrease`() {
        // Given: Um filamento com peso total
        val filament = Filament(
            brand = mockBrand(),
            type = mockType(),
            color = mockColor(),
            totalWeight = 1000,
            remainingWeight = 1000,
            cost = BigDecimal(50.0),
            purchaseDate = LocalDate.now()
        )
        val savedFilament = filamentService.createFilament(filament)

        // When: Registramos um consumo de 200g
        filamentService.registerConsumption(savedFilament.id, 200)

        // Then: O peso restante deve ser atualizado corretamente
        val updatedFilament = filamentService.getFilamentById(savedFilament.id)
        assertThat(updatedFilament.remainingWeight).isEqualTo(800)
    }

    @Test
    fun `Given a filament with limited stock When an over-consumption is attempted Then it should throw an exception`() {
        // Given: Um filamento com apenas 500g restantes
        val filament = Filament(
            brand = mockBrand(),
            type = mockType(),
            color = mockColor(),
            totalWeight = 500,
            remainingWeight = 500,
            cost = BigDecimal(50.0),
            purchaseDate = LocalDate.now()
        )
        val savedFilament = filamentService.createFilament(filament)

        // When: Tentamos consumir 600g
        val exception = org.junit.jupiter.api.assertThrows<RuntimeException> {
            filamentService.registerConsumption(savedFilament.id, 600)
        }

        // Then: Deve lançar uma exceção com a mensagem correta
        assertThat(exception).hasMessage("Not enough filament available")
    }

    @Test
    fun `Given multiple filaments When retrieving all Then should return the correct list`() {
        // Given: Criamos dois filamentos no banco
        val filament1 = filamentService.createFilament(Filament(
            brand = mockBrand("Brand 1"),
            type = mockType(),
            color = mockColor(),
            totalWeight = 1000,
            remainingWeight = 1000,
            cost = BigDecimal(50.0),
            purchaseDate = LocalDate.now()
        ))

        val filament2 = filamentService.createFilament(Filament(
            brand = mockBrand("Brand 2"),
            type = mockType(),
            color = mockColor(),
            totalWeight = 500,
            remainingWeight = 500,
            cost = BigDecimal(30.0),
            purchaseDate = LocalDate.now()
        ))

        // When: Buscamos todos os filamentos
        val filaments = filamentService.getAllFilaments()

        // Then: A lista deve conter exatamente os dois filamentos criados
        assertThat(filaments).isNotNull
        assertThat(filaments).hasSize(2)
        assertThat(filaments.map { it.id }).containsExactlyInAnyOrder(filament1.id, filament2.id)
    }

    @Test
    fun `Given an existing filament When it is deleted Then it should not be retrievable`() {
        // Given: Criamos um filamento no banco
        val filament = filamentService.createFilament(Filament(
            brand = mockBrand(),
            type = mockType(),
            color = mockColor(),
            totalWeight = 1000,
            remainingWeight = 1000,
            cost = BigDecimal(50.0),
            purchaseDate = LocalDate.now()
        ))

        // When: Deletamos o filamento
        filamentService.deleteFilament(filament.id)

        // Then: O filamento não deve mais existir no banco
        val exception = org.junit.jupiter.api.assertThrows<RuntimeException> {
            filamentService.getFilamentById(filament.id)
        }

        assertThat(exception).hasMessage("Filament not found")
    }

    @Test
    fun `Given a non-existent filament When deletion is attempted Then it should throw an exception`() {
        // Given: Um ID de filamento que não existe
        val nonExistentId = 9999

        // When: Tentamos deletar esse ID
        val exception = org.junit.jupiter.api.assertThrows<RuntimeException> {
            filamentService.deleteFilament(nonExistentId)
        }

        // Then: Deve lançar uma exceção com a mensagem correta
        assertThat(exception).hasMessage("Filament not found")
    }

    private fun mockBrand(name: String = "Mock Brand ${System.currentTimeMillis()}"): FilamentBrand {
        return filamentBrandRepository.save(FilamentBrand(name = name))
    }
    private fun mockType(name: String = "Mock Type ${System.currentTimeMillis()}"): FilamentType {
      return filamentTypeRepository.save(FilamentType(name = name))
    }
    private fun mockColor(name: String = "Mock Color ${System.currentTimeMillis()}"): FilamentColor {
        return filamentColorRepository.save(FilamentColor(name = name))
    }
}