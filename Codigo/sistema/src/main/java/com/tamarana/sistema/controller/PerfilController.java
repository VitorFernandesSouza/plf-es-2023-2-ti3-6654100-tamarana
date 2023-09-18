package com.tamarana.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public String perfil(Model model,HttpServletRequest request) {
        String nomeUsuario = CookieService.getCookie(request, "nomeUsuario");
        if (nomeUsuario != null) {
            model.addAttribute("logado", true);
             model.addAttribute("nomeUsuario", nomeUsuario);
            return "perfil/perfil";
        }
        return "redirect:/login";
    }
    
}
