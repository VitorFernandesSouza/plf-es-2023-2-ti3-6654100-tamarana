package com.tamarana.sistema.services;

import java.util.List;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
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

    public boolean adicionarProduto(int idProduto, Usuario usuario) {
        Produto produto = produtoRep.findById(idProduto).get();
        Carrinho carrinho = carrinhoRep.findByUsuarioAndProduto(usuario, produto);
    
        if (carrinho != null) {      
            System.out.println("item ja esta no carrinho");
        } else {
            carrinho = new Carrinho();
            carrinho.setUsuario(usuario);
            carrinho.setProduto(produto);
        }
        carrinhoRep.save(carrinho);
        return true;
    }


}
