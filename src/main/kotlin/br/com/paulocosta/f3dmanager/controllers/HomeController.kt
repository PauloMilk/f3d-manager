package br.com.paulocosta.f3dmanager.controllers

import br.com.paulocosta.f3dmanager.services.FilamentService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(private val filamentService: FilamentService) {

    @GetMapping("/")
    fun home(model: Model): String {
        val filaments = filamentService.getAllFilaments()
        val totalFilaments = filaments.size
        val totalWeight = filaments.sumOf { it.totalWeight }
        val remainingWeight = filaments.sumOf { it.remainingWeight }

        model.addAttribute("totalFilaments", totalFilaments)
        model.addAttribute("totalWeight", totalWeight)
        model.addAttribute("remainingWeight", remainingWeight)

        return "index"
    }
}