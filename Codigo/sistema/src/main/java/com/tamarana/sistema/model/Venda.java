package com.tamarana.sistema.model;
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


    public Venda() {
    }

    public Venda(int id, Usuario usuario, double valor) {
        this.id = id;
        this.usuario = usuario;
        this.valor = valor;
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
