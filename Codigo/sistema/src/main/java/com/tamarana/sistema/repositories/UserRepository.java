package com.tamarana.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tamarana.sistema.model.usuario.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer>{
    Usuario findByEmail(String email);

    @Query(value="SELECT * FROM usuario WHERE email = :email AND senha = :senha", nativeQuery = true)
    public Usuario Login(String email, String senha);
}
