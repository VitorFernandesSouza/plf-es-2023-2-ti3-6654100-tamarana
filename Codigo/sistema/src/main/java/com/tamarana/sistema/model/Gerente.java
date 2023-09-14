package com.tamarana.sistema.model;

import com.tamarana.sistema.model.usuario.Usuario;

public class Gerente extends Usuario {
    int id_gerente;
    public Gerente() {
        super();
    }

    public int getId_gerente() {
        return this.id_gerente;
    }

    public void setId_gerente(int id_gerente) {
        this.id_gerente = id_gerente;
    }


    
}
