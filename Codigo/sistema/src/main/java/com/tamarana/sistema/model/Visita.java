package com.tamarana.sistema.model;
import java.time.LocalDate;
import java.time.LocalTime;

import com.tamarana.sistema.model.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Table(name="visita")
@Entity(name="visita")
public class Visita {
    @Id 
    private int id;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate dataVisita;

    @Column(nullable = false)
    private LocalTime horas;


    public Visita() {
    }

    public Visita(LocalDate dataVisita, LocalTime horas) {
        this.dataVisita = dataVisita;
        this.horas = horas;
    }

    public int getId() {
        return this.id;
    }


    public LocalDate getDataVisita() {
        return this.dataVisita;
    }

    public void setdataVisita(LocalDate dataVisita) {
        this.dataVisita = dataVisita;
    }

    public LocalTime getHoras() {
        return this.horas;
    }

    public void setHoras(LocalTime horas) {
        this.horas = horas;
    }



    public Visita dataVisita(LocalDate dataVisita) {
        setdataVisita(dataVisita);
        return this;
    }

    public Visita horas(LocalTime horas) {
        setHoras(horas);
        return this;
    }


    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", dataVisita='" + getDataVisita() + "'" +
            ", horas='" + getHoras() + "'" +
            "}";
    }
    

}
