package com.tamarana.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamarana.sistema.model.Visita;

public interface VisitaRep extends JpaRepository<Visita, Integer> {
    
}