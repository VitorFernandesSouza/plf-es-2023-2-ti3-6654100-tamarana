package com.tamarana.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.tamarana.sistema.model.Animal;

public interface AnimalRep extends JpaRepository<Animal, Integer> {
    
}
