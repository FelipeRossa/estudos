package com.fsr.security.service;

import com.fsr.security.dto.UsuarioDTO;
import com.fsr.security.security.TokenRec;

public interface IUsuarioService {

	public UsuarioDTO addUsuario(UsuarioDTO user);

	public UsuarioDTO getByUsername(String username);

	public TokenRec userLogin(UsuarioDTO user);
}
