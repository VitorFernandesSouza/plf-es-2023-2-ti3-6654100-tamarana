package com.tamarana.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tamarana.sistema.model.Visita;
import com.tamarana.sistema.model.usuario.Usuario;
import com.tamarana.sistema.repositories.UserRepository;
import com.tamarana.sistema.repositories.VisitaRep;
import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class PerfilController {
    @Autowired
    VisitaRep repVisita;

    @Autowired
    UserRepository repUsuario;

    @GetMapping("/perfil")
    public String perfil(Model model, HttpServletRequest request) {
        String nomeUsuario = CookieService.getCookie(request, "nomeUsuario");
        int idUsuario = Integer.parseInt(CookieService.getCookie(request, "id"));
        if (nomeUsuario != null) {
            model.addAttribute("logado", true);
            model.addAttribute("nomeUsuario", nomeUsuario);
            model.addAttribute("idUsuario", idUsuario);
            return "perfil/perfil";
        }
        return "redirect:/login";
    }

    @PostMapping("/perfil/{id}/agendarvisita")
    public String agendarVisita(Model model, HttpServletRequest request, Visita visita, @PathVariable int id) {
        try {
            System.out.println(visita.getId());
            System.out.println(visita.getUsuario());
            Usuario user = repUsuario.findById(id).get();
            
            visita.setUsuario(user);
            System.out.println(visita.getUsuario());
            System.out.println(visita.getId());

            repVisita.save(visita);
            System.out.println("deubom");
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao agendar visita, tente novamente");
             System.out.println("deuruim");
            e.printStackTrace();
            return "/perfil";
        }
        return "redirect:/perfil";
    }
    
}
