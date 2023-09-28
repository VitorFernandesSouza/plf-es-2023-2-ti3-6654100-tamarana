package com.tamarana.sistema.model;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="animal")
@Entity(name="animal")
public class Animal {
    @Id 
    private int id;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String raca;

    @Column(nullable = false)
    private String sexo;

    private double peso;
    
    @Column(nullable = false)
    private LocalDate dataNasc;

    public Animal() {
    }

    public Animal(int numero, String raca, String sexo, double peso, LocalDate dataNasc) {
        this.numero = numero;
        this.raca = raca;
        this.sexo = sexo;
        this.peso = peso;
        this.dataNasc = dataNasc;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    public String getRaca() {
        return this.raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public LocalDate getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", numero='" + getNumero() + "'" +
            ", raca='" + getRaca() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", peso='" + getPeso() + "'" +
            ", dataNasc='" + getDataNasc() + "'" +
            "}";
    }
   
}
