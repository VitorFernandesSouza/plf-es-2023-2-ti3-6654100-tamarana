package com.tamarana.sistema.model.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
    
@Table(name="usuario")
@Entity(name="usuario")
public class Usuario {
    @Id
    private int id;
    private String primeiro_nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String role;


    public Usuario(String email, String nome, String sobrenome, String senha) {
        this.email = email;
        this.primeiro_nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.role = "cliente";
    }

    public Usuario(String email, String nome, String sobrenome, String senha, String role) {
        this.email = email;
        this.primeiro_nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.role = role;
    }

    public Usuario() {
        this.role = "cliente";
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.primeiro_nome;
    }

    public void setNome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id_usuario) {
        this.id = id_usuario;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role=role;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", primeiro_nome='" + getNome() + "'" +
            ", sobrenome='" + getSobrenome() + "'" +
            ", email='" + getEmail() + "'" +
            ", senha='" + getSenha() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }

}
