package br.com.paulocosta.f3dmanager.controllers

import br.com.paulocosta.f3dmanager.entities.FilamentColor
import br.com.paulocosta.f3dmanager.services.FilamentColorService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/colors")
class FilamentColorController(private val filamentColorService: FilamentColorService) {

    @GetMapping
    fun listColors(model: Model): String {
        model.addAttribute("colors", filamentColorService.getAllColors())
        return "colors"
    }

    @GetMapping("/new")
    fun newColorForm(model: Model): String {
        model.addAttribute("color", FilamentColor(name = ""))
        return "color-form"
    }

    @PostMapping
    fun saveColor(@RequestParam name: String): String {
        filamentColorService.createColor(FilamentColor(name = name))
        return "redirect:/colors"
    }

    @GetMapping("/delete/{id}")
    fun deleteColor(@PathVariable id: Int): String {
        filamentColorService.deleteColor(id)
        return "redirect:/colors"
    }
}