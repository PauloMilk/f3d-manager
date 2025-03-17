package br.com.paulocosta.f3dmanager.controllers

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.support.RequestContextUtils
import java.util.*

@RestController
@RequestMapping("/api/locale")
class LocaleController {

    @PostMapping("/change")
    fun changeLanguage(
        @RequestParam("lang") lang: String,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Map<String, String> {
        val localeResolver: LocaleResolver? = RequestContextUtils.getLocaleResolver(request)
        localeResolver?.setLocale(request, response, Locale(lang))

        return mapOf("message" to "Idioma alterado para $lang")
    }
}