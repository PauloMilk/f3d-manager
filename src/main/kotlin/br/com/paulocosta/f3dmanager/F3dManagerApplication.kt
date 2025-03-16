package br.com.paulocosta.f3dmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class F3dManagerApplication

fun main(args: Array<String>) {
    runApplication<F3dManagerApplication>(*args)
}
