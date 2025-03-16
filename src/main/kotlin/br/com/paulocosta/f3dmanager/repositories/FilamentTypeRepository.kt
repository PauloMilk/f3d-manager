package br.com.paulocosta.f3dmanager.repositories

import br.com.paulocosta.f3dmanager.entities.FilamentType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilamentTypeRepository: JpaRepository<FilamentType, Int>