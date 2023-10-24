package com.tamarana.sistema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamarana.sistema.model.usuario.Usuario;
import com.tamarana.sistema.model.Carrinho;
import com.tamarana.sistema.model.Produto;
import com.tamarana.sistema.repositories.CarrinhoRep;
import com.tamarana.sistema.repositories.ProdutoRep;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRep carrinhoRep;

    @Autowired
    private ProdutoRep produtoRep;

    public List<Carrinho> listarCarrinho(Usuario usuario) {
        return carrinhoRep.findByUsuario(usuario);
    }

    public boolean adicionarProduto(int idProduto, Usuario usuario, int quantidade) {
        System.out.println("chamei");
        Produto produto = produtoRep.findById(idProduto).get();
        System.out.println("achei produto");
        Carrinho carrinho = carrinhoRep.findByUsuarioAndProduto(usuario, produto);
        System.out.println("achei carrinho pelo usuario e produto");
    
    
            carrinho = new Carrinho();
            carrinho.setUsuario(usuario);
            carrinho.setProduto(produto);
            carrinho.setQuantidade(quantidade);
        
        carrinhoRep.save(carrinho);
        return true;
    }

    public boolean removerProduto(int idProduto, Usuario usuario) {
        Produto produto = produtoRep.findById(idProduto).get();
        Carrinho carrinho = carrinhoRep.findByUsuarioAndProduto(usuario, produto);
        if (carrinho != null) {
            carrinhoRep.deleteById(carrinho.getId());
            return true;
        } else {
            System.out.println("Item nao esta no carrinho");
            return false;
        }

    }

    

}
