package br.com.paulocosta.f3dmanager.controllers

import br.com.paulocosta.f3dmanager.entities.*
import br.com.paulocosta.f3dmanager.services.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/filaments")
class FilamentController(
    private val filamentService: FilamentService,
    private val filamentBrandService: FilamentBrandService,
    private val filamentTypeService: FilamentTypeService,
    private val filamentColorService: FilamentColorService
) {

    @GetMapping
    fun listFilaments(model: Model): String {
        model.addAttribute("filaments", filamentService.getAllFilaments())
        return "filaments"
    }

    @GetMapping("/new")
    fun newFilamentForm(model: Model): String {
        val brands = filamentBrandService.getAllBrands()
        val types = filamentTypeService.getAllTypes()
        val colors = filamentColorService.getAllColors()

        if (brands.isEmpty() || types.isEmpty() || colors.isEmpty()) {
            throw IllegalStateException("You must register at least one Brand, Type, and Color before adding a Filament.")
        }

        // Criando um objeto Filament com instâncias vazias de Brand, Type e Color
        val emptyFilament = Filament(
            id = 0,
            brand = brands.first(), // Pegamos a primeira marca existente
            type = types.first(),   // Pegamos o primeiro tipo existente
            color = colors.first(), // Pegamos a primeira cor existente
            totalWeight = 0,
            remainingWeight = 0,
            cost = null,
            purchaseDate = null
        )

        model.addAttribute("filament", emptyFilament)
        model.addAttribute("brands", brands)
        model.addAttribute("types", types)
        model.addAttribute("colors", colors)
        return "filament-form"
    }

    @PostMapping
    fun saveFilament(
        @RequestParam brandId: Int,
        @RequestParam typeId: Int,
        @RequestParam colorId: Int,
        @RequestParam totalWeight: Int,
        @RequestParam remainingWeight: Int
    ): String {
        val brand = filamentBrandService.getAllBrands().find { it.id == brandId }
        val type = filamentTypeService.getAllTypes().find { it.id == typeId }
        val color = filamentColorService.getAllColors().find { it.id == colorId }

        if (brand == null || type == null || color == null) {
            throw IllegalArgumentException("Brand, Type, or Color not found!")
        }

        val filament = Filament(
            id = 0,
            brand = brand,
            type = type,
            color = color,
            totalWeight = totalWeight,
            remainingWeight = remainingWeight,
            cost = null,
            purchaseDate = null
        )

        filamentService.createFilament(filament)
        return "redirect:/filaments"
    }

    @GetMapping("/delete/{id}")
    fun deleteFilament(@PathVariable id: Int): String {
        filamentService.deleteFilament(id)
        return "redirect:/filaments"
    }

    @GetMapping("/edit/{id}")
    fun editFilamentForm(@PathVariable id: Int, model: Model): String {
        val filament = filamentService.getFilamentById(id)

        model.addAttribute("filament", filament)
        model.addAttribute("brands", filamentBrandService.getAllBrands())
        model.addAttribute("types", filamentTypeService.getAllTypes())
        model.addAttribute("colors", filamentColorService.getAllColors())

        return "edit-filament"
    }

    @PostMapping("/update")
    fun updateFilament(
        @RequestParam id: Int,
        @RequestParam brandId: Int,
        @RequestParam typeId: Int,
        @RequestParam colorId: Int
    ): String {
        val filament = filamentService.getFilamentById(id)

        val brand = filamentBrandService.getBrandById(brandId)
        val type = filamentTypeService.getTypeById(typeId)
        val color = filamentColorService.getColorById(colorId)

        if (brand == null || type == null || color == null) {
            throw IllegalArgumentException("Brand, Type, or Color not found!")
        }

        val updatedFilament = Filament(
            id = filament.id,
            brand = brand,
            type = type,
            color = color,
            totalWeight = filament.totalWeight, // Mantemos o valor original
            remainingWeight = filament.remainingWeight, // Mantemos o valor original
            cost = filament.cost,
            purchaseDate = filament.purchaseDate
        )

        filamentService.updateFilament(updatedFilament)
        return "redirect:/filaments"
    }

    @GetMapping("/consume/{id}")
    fun consumeFilamentForm(@PathVariable id: Int, model: Model): String {
        val filament = filamentService.getFilamentById(id)
        model.addAttribute("filament", filament)
        return "consume-filament"
    }

    @PostMapping("/consume")
    fun registerConsumption(
        @RequestParam id: Int,
        @RequestParam consumedAmount: Int
    ): String {
        filamentService.registerConsumption(id, consumedAmount)
        return "redirect:/filaments"
    }

    @GetMapping("/purchase/{id}")
    fun purchaseFilamentForm(@PathVariable id: Int, model: Model): String {
        val filament = filamentService.getFilamentById(id)
        model.addAttribute("filament", filament)
        return "purchase-filament"
    }

    @PostMapping("/purchase")
    fun registerPurchase(
        @RequestParam id: Int,
        @RequestParam purchasedAmount: Int,
        @RequestParam(required = false) cost: Double?
    ): String {
        filamentService.registerPurchase(id, purchasedAmount, cost)
        return "redirect:/filaments"
    }
}