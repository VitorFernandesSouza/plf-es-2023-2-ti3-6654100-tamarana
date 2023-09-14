package com.tamarana.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tamarana.sistema.model.usuario.Usuario;
import com.tamarana.sistema.repositories.UserRepository;

@Controller
public class CadastroController {
    @Autowired
    private UserRepository rep;

    @GetMapping("/cadastro")
    public String index() {
        return "cadastro/index";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Usuario usuarioParam, Model model) {
        try {
            rep.save(usuarioParam);
            model.addAttribute("sucesso", true);
        } catch (NonTransientDataAccessException e) {
            model.addAttribute("erro", "Este email já foi cadastrado!");
        } 

        return "cadastro/index";
    }

}
