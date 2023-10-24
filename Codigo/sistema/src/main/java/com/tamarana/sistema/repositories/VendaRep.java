package com.tamarana.sistema.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tamarana.sistema.model.Venda;

import com.tamarana.sistema.model.usuario.Usuario;

public interface VendaRep extends JpaRepository<Venda, Integer> {
    public List<Venda> findByUsuario (Usuario usuario);
    
    // @Query(value="SELECT * FROM usuario WHERE email = :email AND senha = :senha", nativeQuery = true)
    // public Usuario Login(String email, String senha);


  

    

}
