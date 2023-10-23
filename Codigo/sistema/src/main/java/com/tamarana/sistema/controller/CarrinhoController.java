package com.tamarana.sistema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.tamarana.sistema.model.Carrinho;
import com.tamarana.sistema.model.usuario.Usuario;
import com.tamarana.sistema.repositories.UserRepository;
import com.tamarana.sistema.services.CarrinhoService;
import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private UserRepository usuarioRep;

    @GetMapping("/perfil/carrinho")
    public String mostrarCarrinho(Model model, HttpServletRequest request) {
        String nomeUsuario = CookieService.getCookie(request, "nomeUsuario");
        if (nomeUsuario != null) {
            model.addAttribute("logado", true);
            String emailUsuario = CookieService.getCookie(request, "emailUsuario");
            Usuario usuario = usuarioRep.findByEmail(emailUsuario);
            List<Carrinho> carrinho = carrinhoService.listarCarrinho(usuario);
        
            model.addAttribute("nomeUsuario", nomeUsuario);
            model.addAttribute("carrinho", carrinho);
            return "perfil/carrinho";

        }
        return "redirect:/login";
    }

    @PostMapping("/perfil/carrinho/adicionar/{id}")
     public String adicionarProduto(@PathVariable("id") int idProduto,  HttpServletRequest request, Model model) {
        String emailUsuario = CookieService.getCookie(request, "emailUsuario");
        // int idProduto = Integer.parseInt(idProd);
   

        if (emailUsuario != null) {
            Usuario usuario = usuarioRep.findByEmail(emailUsuario);
            carrinhoService.adicionarProduto(idProduto, usuario);
            System.out.println("item adicionado ao seu carrinho!");
         
            return "redirect:/produtos";
        }

        return "redirect:/login";
    }
}
