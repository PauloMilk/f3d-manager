package br.com.paulocosta.f3dmanager.controllers

import br.com.paulocosta.f3dmanager.entities.FilamentBrand
import br.com.paulocosta.f3dmanager.services.FilamentBrandService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/brands")
class FilamentBrandController(private val filamentBrandService: FilamentBrandService) {

    @GetMapping
    fun listBrands(model: Model): String {
        model.addAttribute("brands", filamentBrandService.getAllBrands())
        return "brands"
    }

    @GetMapping("/new")
    fun newBrandForm(model: Model): String {
        model.addAttribute("brand", FilamentBrand(name = ""))
        return "brand-form"
    }

    @PostMapping
    fun saveBrand(@RequestParam name: String): String {
        filamentBrandService.createBrand(FilamentBrand(name = name))
        return "redirect:/brands"
    }

    @GetMapping("/delete/{id}")
    fun deleteBrand(@PathVariable id: Int): String {
        filamentBrandService.deleteBrand(id)
        return "redirect:/brands"
    }
}