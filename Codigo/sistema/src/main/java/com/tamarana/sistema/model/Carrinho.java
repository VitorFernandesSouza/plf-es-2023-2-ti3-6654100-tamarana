package com.tamarana.sistema.model;


import com.tamarana.sistema.model.usuario.Usuario;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Table(name="carrinho")
@Entity(name="carrinho")
public class Carrinho {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name="produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    private int quantidade;

    @Nonnull
    private int confirmada;

 

    public Carrinho() {
        this.confirmada = 0;
    }

    public Carrinho(Produto produto, Usuario usuario, int quantidade) {
        this.produto = produto;
        this.usuario = usuario;
        this.quantidade = quantidade;
        this.confirmada = 0;
   
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Carrinho id(int id) {
        setId(id);
        return this;
    }

    public Carrinho produto(Produto produto) {
        setProduto(produto);
        return this;
    }

    public Carrinho usuario(Usuario usuario) {
        setUsuario(usuario);
        return this;
    }

    public Carrinho quantidade(int quantidade) {
        setQuantidade(quantidade);
        return this;
    }


    public int getConfirmada() {
        return this.confirmada;
    }

    public void setConfirmada(int confirmada) {
        this.confirmada = confirmada;
    }
  

    @Transient
    public double getSubtotal() {
        return this.produto.getPreco()*quantidade;
    }




    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", produto='" + getProduto() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            "}";
    }
    
}
