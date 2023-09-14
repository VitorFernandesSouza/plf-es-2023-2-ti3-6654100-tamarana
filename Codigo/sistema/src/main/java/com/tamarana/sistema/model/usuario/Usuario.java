package com.tamarana.sistema.model.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
    
@Table(name="usuario")
@Entity(name="usuario")
public class Usuario {
    @Id
    private int id_usuario;
    private String primeiro_nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String role;


    public Usuario(int id_usuario, String email, String nome, String sobrenome, String senha) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.primeiro_nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.role = "usuario";
    }

    public Usuario() {
        this.role = "usuario";
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

    public int getId_usuario() {
        return this.id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRole() {
        return this.role;
    }


}
