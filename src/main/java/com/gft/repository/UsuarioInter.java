package com.gft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.model.Usuario;

public interface UsuarioInter extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);
}
