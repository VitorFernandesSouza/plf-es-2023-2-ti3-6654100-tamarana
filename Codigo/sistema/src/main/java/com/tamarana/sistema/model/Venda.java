package com.tamarana.sistema.model;
import java.util.List;

import com.tamarana.sistema.model.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="venda")
@Entity(name="venda")
public class Venda {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    private double valor;
    private String descricao;
    private String status;


    public Venda() {
       this.status = "pendente";
    }

    public Venda(Usuario usuario, double valor, List<Produto> listaProdutos) {
        this.usuario = usuario;
        this.valor = valor;
        
    }

    public Venda(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Venda id(int id) {
        setId(id);
        return this;
    }

    public Venda usuario(Usuario usuario) {
        setUsuario(usuario);
        return this;
    }

    public Venda valor(double valor) {
        setValor(valor);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }


}
