package br.com.paulocosta.f3dmanager.repositories

import br.com.paulocosta.f3dmanager.entities.FilamentColor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilamentColorRepository : JpaRepository<FilamentColor, Int> {
    fun existsByName(name: String): Boolean
}