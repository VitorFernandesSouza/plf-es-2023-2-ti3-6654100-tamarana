package com.tamarana.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        String cookie = CookieService.getCookie(request, "nomeUsuario");
        if (cookie != null) {
            model.addAttribute("nomeUsuario", cookie);
             model.addAttribute("logado", true);
            return "home";
        }
        return "home";
    }   

    @GetMapping("/home")
    public String home(Model model,HttpServletRequest request) {
        String cookie = CookieService.getCookie(request, "nomeUsuario");
        if (cookie != null) {
            model.addAttribute("logado", true);
            return "redirect:/";
        }
        return "redirect:/";
    }
}
