package com.tamarana.sistema.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamarana.sistema.model.Carrinho;
import com.tamarana.sistema.model.Produto;
import com.tamarana.sistema.model.usuario.Usuario;

public interface CarrinhoRep extends JpaRepository<Carrinho, Integer> {
    public List<Carrinho> findByUsuario (Usuario usuario);
    public Carrinho findByUsuarioAndProduto(Usuario usuario, Produto produto);
}
