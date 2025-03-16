package br.com.paulocosta.f3dmanager.repositories

import br.com.paulocosta.f3dmanager.entities.Filament
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilamentRepository: JpaRepository<Filament, Int>