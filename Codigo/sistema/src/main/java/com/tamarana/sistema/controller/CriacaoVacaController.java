package com.tamarana.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CriacaoVacaController {

    @GetMapping("/criacaoVaca")
    public String showCriacaoVacaPage() {
        // Aqui você pode adicionar lógica adicional, se necessário, antes de retornar o nome da página HTML
        return "criacaoVaca";
    }
}
