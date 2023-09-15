// Controle acoes relacionadas a paginda admin.html
package com.tamarana.sistema.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tamarana.sistema.model.usuario.Usuario;
import com.tamarana.sistema.repositories.UserRepository;
import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


@Controller
public class LoginController {

    @Autowired
    private UserRepository rep;
        
    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String id = CookieService.getCookie(request, "id");
        if (id != null) {
            return "redirect:/perfil";
        }
        return "login/index";
    }

    @PostMapping("/logar")
    public String logar(Model model, Usuario usuarioParam, String lembrar, HttpServletResponse response) {
        Usuario usuario = this.rep.Login(usuarioParam.getEmail(), usuarioParam.getSenha());
        if (usuario != null) {
            int tempoLogado = 60*60;
            if (lembrar != null) tempoLogado = (60*60*24*365); // se lembrar senha, dura 1 ano
            CookieService.setCookie(response, "id", String.valueOf(usuario.getId()), tempoLogado);
            CookieService.setCookie(response, "emailUsuario", usuario.getEmail(), tempoLogado);
            CookieService.setCookie(response, "nomeUsuario", usuario.getNome(), tempoLogado);
            CookieService.setCookie(response, "sobrenomeUsuario", usuario.getSobrenome(), tempoLogado);
            CookieService.setCookie(response, "role", usuario.getRole(), tempoLogado);
            return "redirect:/perfil";
        }

        model.addAttribute("erro", "Usuário ou senha inválidos");
        
        return "login/index";
    }

    @GetMapping("/sair")
    public String sair(HttpServletResponse response) {
        CookieService.setCookie(response, "id", "", 0);
        CookieService.setCookie(response, "emailUsuario", "", 0);
        CookieService.setCookie(response, "nomeUsuario", "", 0);
        CookieService.setCookie(response, "sobrenomeUsuario", "", 0);
        CookieService.setCookie(response, "role", "", 0);
        return "redirect:/login";
    }


}