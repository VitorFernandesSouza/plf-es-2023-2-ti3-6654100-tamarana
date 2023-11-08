// Controle acoes relacionadas a paginda admin.html
package com.tamarana.sistema.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.function.RequestPredicates.Visitor;

import com.tamarana.sistema.model.Animal;

import com.tamarana.sistema.model.Produto;
import com.tamarana.sistema.model.Venda;
import com.tamarana.sistema.model.Visita;
import com.tamarana.sistema.model.usuario.Usuario;
import com.tamarana.sistema.repositories.AnimalRep;

import com.tamarana.sistema.repositories.ProdutoRep;
import com.tamarana.sistema.repositories.UserRepository;
import com.tamarana.sistema.repositories.VendaRep;
import com.tamarana.sistema.repositories.VisitaRep;
import com.tamarana.sistema.services.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdminController {

    @Autowired
    private UserRepository repUsuario;

    @Autowired
    private ProdutoRep repProduto;

    @Autowired
    private AnimalRep repAnimal;

    @Autowired
    private VendaRep repVenda;

     @Autowired
    private VisitaRep repVisita;

    @GetMapping("/admin")
    public String index(Model model, HttpServletRequest request) {
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
                int tempoLogado = 60 * 60;
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
        if (roleAdmin.equals("admin")) {
            List<Usuario> listaUsuarios = (List<Usuario>) repUsuario.findAll();
            model.addAttribute("listaUsuarios", listaUsuarios);
            model.addAttribute("roleAdmin", roleAdmin);
            model.addAttribute("nomeAdmin", nomeAdmin);
            return "admin/gerenciarUsuarios";
        }
        return "admin/login";
    }

    // CADASTRAR USUÁRIO
    @PostMapping("/admin/cadastrarUsuario")
    public String cadastrarUsuario(Usuario usuarioParam, Model model) {
        try {
            repUsuario.save(usuarioParam);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar, volte para a página anterior e tente novamente");
            e.printStackTrace();
            return "admin/gerenciarUsuarios";
        }
        return "redirect:/admin/gerenciarUsuarios";
    }

    // REMOVER USUÁRIO
    @GetMapping("/admin/{id}/removerUsuario")
    public String removerUsuario(@PathVariable int id) {
        try {
            repUsuario.deleteById(id);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/gerenciarUsuarios";
    }

    // EDITAR USUÁRIO
    @PostMapping("/admin/editarUsuario")
    public String eiditarUsuario(Model model, Usuario usuarioParam) {
        try {
            Usuario usuario = repUsuario.getReferenceById(usuarioParam.getId());
            if (usuario != null) {
                if (usuarioParam.getSenha().length() == 0) {
                    usuarioParam.setSenha(usuario.getSenha());
                }
                repUsuario.save(usuarioParam);
            }
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao editar, volte para a página anterior e tente novamente");
            e.printStackTrace();
            return "admin/gerenciarUsuarios";
        }
        return "redirect:/admin/gerenciarUsuarios";
    }

    // GERENCIAR PRODUTOS
    @GetMapping("/admin/gerenciarProdutos")
    public String gerenciarProdutos(Model model, HttpServletRequest request) {
        String roleAdmin = CookieService.getCookie(request, "role");
        String nomeAdmin = CookieService.getCookie(request, "nomeUsuario");
        if (roleAdmin.equals("admin")) {
            List<Produto> listaProdutos = (List<Produto>) repProduto.findAll();
            model.addAttribute("listaProdutos", listaProdutos);
            model.addAttribute("roleAdmin", roleAdmin);
            model.addAttribute("nomeAdmin", nomeAdmin);
            return "admin/gerenciarProdutos";
        }
        return "admin/login";
    }

    // CADASTRAR PRODUTO
    @PostMapping("/admin/cadastrarProduto")
    public String cadastrarProduto(Produto produtoParam, Model model) {
        try {
            repProduto.save(produtoParam);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar, volte para a página anterior e tente novamente");
            e.printStackTrace();
            return "admin/gerenciarProdutos";
        }
        return "redirect:/admin/gerenciarProdutos";
    }

    // REMOVER PRODUTO
    @GetMapping("/admin/{id}/removerProduto")
    public String removerProduto(@PathVariable int id) {
        try {
            repProduto.deleteById(id);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/gerenciarProdutos";
    }

    // EDITAR PRODUTO
    @PostMapping("/admin/editarProduto")
    public String eiditarProduto(Model model, Produto produtoParam) {
        try {
            Produto produto = repProduto.getReferenceById(produtoParam.getId());
            if (produto != null) {
                repProduto.save(produtoParam);
            }
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao editar, volte para a página anterior e tente novamente");
            e.printStackTrace();
            return "admin/gerenciarProdutos";
        }
        return "redirect:/admin/gerenciarProdutos";
    }

    // GERENCIAR ANIMAIS
    @GetMapping("/admin/gerenciarAnimais")
    public String gerenciarAnimais(Model model, HttpServletRequest request) {
        String roleAdmin = CookieService.getCookie(request, "role");
        String nomeAdmin = CookieService.getCookie(request, "nomeUsuario");
        if (roleAdmin.equals("admin")) {
            List<Animal> listaAnimais = (List<Animal>) repAnimal.findAll();
            model.addAttribute("listaAnimais", listaAnimais);
            model.addAttribute("roleAdmin", roleAdmin);
            model.addAttribute("nomeAdmin", nomeAdmin);
            return "admin/gerenciarAnimais";
        }
        return "admin/login";
    }

    // CADASTRAR ANIMAL
    @PostMapping("/admin/cadastrarAnimal")
    public String cadastrarAnimal(Animal animalParam, Model model) {
        try {
            repAnimal.save(animalParam);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar, volte para a página anterior e tente novamente");
            e.printStackTrace();
            return "admin/gerenciarAnimais";
        }
        return "redirect:/admin/gerenciarAnimais";
    }

    // REMOVER ANIMAL
    @GetMapping("/admin/{id}/removerAnimal")
    public String removerAnimal(@PathVariable int id) {
        try {
            repAnimal.deleteById(id);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/gerenciarAnimais";
    }

    // EDITAR ANIMAL
    @PostMapping("/admin/editarAnimal")
    public String eiditarAnimal(Model model, Animal animalParam) {
        try {
            Animal animal = repAnimal.getReferenceById(animalParam.getId());
            System.out.println(animalParam);
            if (animal != null) {
                repAnimal.save(animalParam);
            }
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao editar, volte para a página anterior e tente novamente");
            e.printStackTrace();
            return "admin/gerenciarAnimais";
        }
        return "redirect:/admin/gerenciarAnimais";
    }

    // GERENCIAR PEDIDOS
    @GetMapping("/admin/gerenciarPedidos")
    public String gerenciarPedidos(Model model, HttpServletRequest request) {
        String roleAdmin = CookieService.getCookie(request, "role");
        String nomeAdmin = CookieService.getCookie(request, "nomeUsuario");
        if (roleAdmin.equals("admin")) {
            List<Venda> listaVendas = (List<Venda>) repVenda.findAll();
            model.addAttribute("listaVendas", listaVendas);
          
       
            model.addAttribute("roleAdmin", roleAdmin);
            model.addAttribute("nomeAdmin", nomeAdmin);
            return "admin/gerenciarPedidos";
        }
        return "admin/login";
    }

    // CONFIRMAR PEDIDO
    @GetMapping("/admin/{id}/confirmar")
    public String confirmarPedido(@PathVariable int id) {
        System.out.println(id);
        try {
           Venda venda =  repVenda.findById(id).get();
           venda.setStatus("confirmada");
           repVenda.save(venda);
        
        } catch (Exception e) {
           
            e.printStackTrace();
            return "redirect:/admin/gerenciarPedidos";
        }
        return "redirect:/admin/gerenciarPedidos";
    }

     // REMOVER PEDIDO
    @GetMapping("/admin/{id}/removerPedido")
    public String removerPedido(@PathVariable int id) {
        try {
            repVenda.deleteById(id);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/gerenciarPedidos";
    }

      // GERENCIAR VISITAS
    @GetMapping("/admin/gerenciarVisitas")
    public String gerenciarVisitas(Model model, HttpServletRequest request) {
        String roleAdmin = CookieService.getCookie(request, "role");
        String nomeAdmin = CookieService.getCookie(request, "nomeUsuario");
        if (roleAdmin.equals("admin")) {
            List<Visita> listaVisitas = (List<Visita>) repVisita.findAll();
            model.addAttribute("listaVisitas", listaVisitas);
          
       
            model.addAttribute("roleAdmin", roleAdmin);
            model.addAttribute("nomeAdmin", nomeAdmin);
            return "admin/gerenciarVisitas";
        }
        return "admin/login";
    }

      // REMOVER VISITA
    @GetMapping("/admin/{id}/removerVisita")
    public String removerVisita(@PathVariable int id) {
        try {
            repVisita.deleteById(id);
        } catch (NonTransientDataAccessException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/gerenciarVisitas";
    }

    // HISTORICO VENDAS
    @GetMapping("/admin/historicoVendas")
    public String historicoVendas(Model model, HttpServletRequest request) {
        String roleAdmin = CookieService.getCookie(request, "role");
        String nomeAdmin = CookieService.getCookie(request, "nomeUsuario");
        if (roleAdmin.equals("admin")) {
            List<Venda> listaVendas = (List<Venda>) repVenda.findAll();
            model.addAttribute("listaVendas", listaVendas);
          
       
            model.addAttribute("roleAdmin", roleAdmin);
            model.addAttribute("nomeAdmin", nomeAdmin);
            return "admin/historicoVendas";
        }
        return "admin/login";
    }
}
