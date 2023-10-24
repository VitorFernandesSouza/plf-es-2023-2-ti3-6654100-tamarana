package com.tamarana.sistema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.tamarana.sistema.model.Carrinho;
import com.tamarana.sistema.model.Venda;
import com.tamarana.sistema.model.usuario.Usuario;
import com.tamarana.sistema.repositories.CarrinhoRep;
import com.tamarana.sistema.repositories.UserRepository;
import com.tamarana.sistema.repositories.VendaRep;
import com.tamarana.sistema.services.CarrinhoService;
import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private UserRepository usuarioRep;

    @Autowired
    private VendaRep vendaRep;

    @Autowired
    private CarrinhoRep carrinhoRep;

    @GetMapping("/perfil/carrinho")
    public String mostrarCarrinho(Model model, HttpServletRequest request) {
        String nomeUsuario = CookieService.getCookie(request, "nomeUsuario");
        int idUsuario = Integer.parseInt(CookieService.getCookie(request, "id"));
        if (nomeUsuario != null) {
            model.addAttribute("logado", true);
            String emailUsuario = CookieService.getCookie(request, "emailUsuario");
            Usuario usuario = usuarioRep.findByEmail(emailUsuario);
            List<Carrinho> carrinho = carrinhoService.listarCarrinho(usuario);
        
            model.addAttribute("nomeUsuario", nomeUsuario);
            model.addAttribute("idUsuario", idUsuario);
            model.addAttribute("carrinho", carrinho);

            boolean vendaPendente = false;
            for(Carrinho item : carrinho) {
                System.out.println(item.getConfirmada());
                if (item.getConfirmada() == 0) {
                    vendaPendente = true;
                }
            }

            System.out.println(vendaPendente);

            if (vendaPendente) {
                model.addAttribute("vendaPendente", true);
            } else {
                model.addAttribute("vendaPendente", false);
            }

            if (carrinho.isEmpty()) {
                model.addAttribute("vendaPendente", false);
            }

            return "perfil/carrinho";

        }
        return "redirect:/login";
    }

    // @PostMapping("/perfil/carrinho/adicionar/{id}")
    //  public String adicionarProduto(@PathVariable("id") int idProduto,  HttpServletRequest request, Model model) {
    //     String emailUsuario = CookieService.getCookie(request, "emailUsuario");
    //     if (emailUsuario != null) {
    //         Usuario usuario = usuarioRep.findByEmail(emailUsuario);
    //         carrinhoService.adicionarProduto(idProduto, usuario);
    //         System.out.println("item adicionado ao seu carrinho!");
         
    //         return "redirect:/produtos";
    //     }

    //     return "redirect:/login";
    // }

    @PostMapping("/perfil/carrinho/adicionar/{id}")
     public String adicionarProduto(@PathVariable("id") int idProduto,  HttpServletRequest request, Model model, Carrinho carrinho) {
        String emailUsuario = CookieService.getCookie(request, "emailUsuario");
        if (emailUsuario != null) {
            Usuario usuario = usuarioRep.findByEmail(emailUsuario);
            int quantidade = carrinho.getQuantidade();
            carrinhoService.adicionarProduto(idProduto, usuario, quantidade);
            System.out.println("item adicionado ao seu carrinho!");
         
            return "redirect:/produtos";
        }

        return "redirect:/login";
    }

     @GetMapping("/perfil/carrinho/remover/{id}")
     public String removerProduto(@PathVariable("id") int idProduto,  HttpServletRequest request, Model model) {
        String emailUsuario = CookieService.getCookie(request, "emailUsuario");
        if (emailUsuario != null) {
            Usuario usuario = usuarioRep.findByEmail(emailUsuario);
            carrinhoService.removerProduto(idProduto, usuario);
            
            return "redirect:/perfil/carrinho";
        }

        return "redirect:/login";
    }

    @PostMapping("/perfil/carrinho/criar-pedido")
    public String criarPedido(Venda venda, HttpServletRequest request, Model model) {
         String emailUsuario = CookieService.getCookie(request, "emailUsuario");

          if (emailUsuario != null) {
            vendaRep.save(venda); 
            List<Carrinho> listaItems = carrinhoRep.findByUsuario(venda.getUsuario());
            for (Carrinho item : listaItems) {
                item.setConfirmada(1);
                carrinhoRep.save(item);
            }
            
           model.addAttribute("vendaPendente", false);
           model.addAttribute("logado", true);
           model.addAttribute("pedidoRealizado", "Seu pedido de compra foi criado com sucesso! Aguarde a confirmação por um funcionário");
            return "perfil/carrinho";
        }

        return "redirect:/login";

      
    }
}
