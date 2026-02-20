package com.fsr.security.repo;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.fsr.security.model.Usuario;

public interface UsuarioRepo extends ListCrudRepository<Usuario, Long> {
	public Optional<Usuario> findByUserName(String username);
}
