package com.tamarana.sistema.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamarana.sistema.model.Venda;
import com.tamarana.sistema.model.usuario.Usuario;

public interface VendaRep extends JpaRepository<Venda, Integer> {
    public List<Venda> findByUsuario (Usuario usuario);

}
