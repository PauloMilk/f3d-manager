package br.com.paulocosta.f3dmanager.controllers

import br.com.paulocosta.f3dmanager.entities.FilamentType
import br.com.paulocosta.f3dmanager.services.FilamentTypeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/types")
class FilamentTypeController(private val filamentTypeService: FilamentTypeService) {

    @GetMapping
    fun listTypes(model: Model): String {
        model.addAttribute("types", filamentTypeService.getAllTypes())
        return "types"
    }

    @GetMapping("/new")
    fun newTypeForm(model: Model): String {
        model.addAttribute("type", FilamentType(name = ""))
        return "type-form"
    }

    @PostMapping
    fun saveType(@RequestParam name: String): String {
        filamentTypeService.createType(FilamentType(name = name))
        return "redirect:/types"
    }

    @GetMapping("/delete/{id}")
    fun deleteType(@PathVariable id: Int): String {
        filamentTypeService.deleteType(id)
        return "redirect:/types"
    }
}