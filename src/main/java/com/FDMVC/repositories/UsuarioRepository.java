package com.FDMVC.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FDMVC.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
    Optional<Usuario> findByEmail(String email);

}
