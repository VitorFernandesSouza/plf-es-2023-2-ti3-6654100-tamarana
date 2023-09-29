package com.tamarana.sistema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tamarana.sistema.model.Produto;
import com.tamarana.sistema.repositories.ProdutoRep;
import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class ProdutosController {
    @Autowired
    ProdutoRep repProduto;

    @GetMapping("/produtos")
    public String produtos(Model model,HttpServletRequest request) {
        String nomeUsuario = CookieService.getCookie(request, "nomeUsuario");
        if (nomeUsuario != null) {
            model.addAttribute("logado", true);
            model.addAttribute("nomeUsuario", nomeUsuario);
            List<Produto> listaProdutos = (List<Produto>) repProduto.findAll();
            model.addAttribute("listaProdutos", listaProdutos);

            return "perfil/produtos";
        }
        return "redirect:/login";
    }
    
}
