// Controle acoes relacionadas a paginda admin.html
package com.tamarana.sistema.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tamarana.sistema.model.Produto;
import com.tamarana.sistema.model.usuario.Usuario;
import com.tamarana.sistema.repositories.ProdutoRep;
import com.tamarana.sistema.repositories.UserRepository;
import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class AdminController {

    @Autowired
    private UserRepository repUsuario;

    @Autowired
    private ProdutoRep repProduto;


    @GetMapping("/admin")
    public String index(Model model,HttpServletRequest request) {
        String roleAdmin = CookieService.getCookie(request, "role");
        if (roleAdmin != null && roleAdmin.equals("admin")) {
            return "redirect:/admin/gerenciarUsuarios";
        }
        return "admin/login";

    }


    // LOGIN
        @PostMapping("/adminLogar")
    public String logar(Model model, Usuario usuarioParam, HttpServletResponse response) {
        Usuario admin = this.repUsuario.Login(usuarioParam.getEmail(), usuarioParam.getSenha());
        if (admin != null) {
            if (admin.getRole().equals("admin")) {
                int tempoLogado = 60*60;
                CookieService.setCookie(response, "id", String.valueOf(admin.getId()), tempoLogado);
                CookieService.setCookie(response, "emailUsuario", admin.getEmail(), tempoLogado);
                CookieService.setCookie(response, "nomeUsuario", admin.getNome(), tempoLogado);
                CookieService.setCookie(response, "sobrenomeUsuario", admin.getSobrenome(), tempoLogado);
                CookieService.setCookie(response, "role", admin.getRole(), tempoLogado);
                return "redirect:/admin/gerenciarUsuarios";
            }  
        }
        model.addAttribute("erro", "Credenciais incorretas");
        
        return "admin/login";
    }

    @GetMapping("/adminSair")
    public String sair(HttpServletResponse response) {
        CookieService.setCookie(response, "id", "", 0);
        CookieService.setCookie(response, "emailUsuario", "", 0);
        CookieService.setCookie(response, "nomeUsuario", "", 0);
        CookieService.setCookie(response, "sobrenomeUsuario", "", 0);
        CookieService.setCookie(response, "role", "", 0);
        return "redirect:/admin";
    }


   // GERENCIAR USUÁRIOS
    @GetMapping("/admin/gerenciarUsuarios")
    public String gerenciarUsuarios(Model model, HttpServletRequest request) {
        String roleAdmin = CookieService.getCookie(request, "role");    
        String nomeAdmin = CookieService.getCookie(request, "nomeUsuario");
        if (roleAdmin.equals("admin")){
            List<Usuario> listaUsuarios = (List<Usuario>)repUsuario.findAll();
            model.addAttribute("listaUsuarios", listaUsuarios);        
            model.addAttribute("roleAdmin", roleAdmin);
            model.addAttribute("nomeAdmin", nomeAdmin);
            return "admin/gerenciarUsuarios";
        }
        return "admin/login";
    }

    @PostMapping("/admin/cadastrarUsuario")
    public String cadastrarUsuario(Usuario usuarioParam, Model model) {
        try {
            repUsuario.save(usuarioParam);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        } 
        return "redirect:/admin/gerenciarUsuarios";
    }

    @GetMapping("/admin/{id}/removerUsuario")
    public String removerUsuario(@PathVariable int id) {
        try {
            repUsuario.deleteById(id);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        } 
        return "redirect:/admin/gerenciarUsuarios";
    }

    @PostMapping("/admin/editarUsuario")
    public String eiditarUsuario(Usuario usuarioParam) {
        try { 
            Usuario usuario = repUsuario.getReferenceById(usuarioParam.getId());  
            if (usuario != null) {
                if (usuarioParam.getSenha().length() == 0) {
                    usuarioParam.setSenha(usuario.getSenha());
                }
                repUsuario.save(usuarioParam);
            }
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        } 
        return "redirect:/admin/gerenciarUsuarios";
    }

    
    // GERENCIAR PRODUTOS
    @GetMapping("/admin/gerenciarProdutos")
    public String gerenciarProdutos(Model model, HttpServletRequest request) {
        String roleAdmin = CookieService.getCookie(request, "role");
        String nomeAdmin = CookieService.getCookie(request, "nomeUsuario");
        if (roleAdmin.equals("admin")){
            List<Produto> listaProdutos = (List<Produto>)repProduto.findAll();
            model.addAttribute("listaProdutos", listaProdutos);
            model.addAttribute("roleAdmin", roleAdmin);
            model.addAttribute("nomeAdmin", nomeAdmin);
            return "admin/gerenciarProdutos";
        }
        return "admin/login";
    }

    @PostMapping("/admin/cadastrarProduto")
    public String cadastrarProduto(Produto produtoParam, Model model) {
        try {
            repProduto.save(produtoParam);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        } 
        return "redirect:/admin/gerenciarProdutos";
    }

    @GetMapping("/admin/{id}/removerProduto")
    public String removerProduto(@PathVariable int id) {
        try {
            repProduto.deleteById(id);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        } 
        return "redirect:/admin/gerenciarProdutos"; 
    }

    @PostMapping("/admin/editarProduto")
    public String eiditarProduto(Produto produtoParam) {
        try { 
            Produto produto = repProduto.getReferenceById(produtoParam.getId());  
            if (produto != null) {
                repProduto.save(produtoParam);
            }
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        } 
        return "redirect:/admin/gerenciarProdutos";
    }


}
