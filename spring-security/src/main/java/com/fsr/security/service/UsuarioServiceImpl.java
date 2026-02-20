package com.fsr.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fsr.security.dto.UsuarioDTO;
import com.fsr.security.mapper.UsuarioMapper;
import com.fsr.security.model.Usuario;
import com.fsr.security.repo.UsuarioRepo;
import com.fsr.security.security.TokenRec;
import com.fsr.security.security.TokenUtil;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private UsuarioRepo repo;

	@Autowired
	private UsuarioMapper mapper;

	public UsuarioServiceImpl(UsuarioRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public UsuarioDTO addUsuario(UsuarioDTO userDto) {
		Usuario user = mapper.toEntity(userDto);
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		user.setSenha(encode.encode(userDto.getSenha()));
		return mapper.toDTO(repo.save(user));
	}

	@Override
	public UsuarioDTO getByUsername(String username) {
		Usuario user = repo.findByUserName(username).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
		return mapper.toDTO(user);
	}

	@Override
	public TokenRec userLogin(UsuarioDTO userDto) {

		Usuario user = repo.findByUserName(userDto.getUserName()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		/*compara a sequencia de caracteres da senha normal com a sequencia de caracteres da senha encriptada*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(userDto.getSenha(), user.getSenha())) {
			return TokenUtil.encode(user);
		}
		
		throw new RuntimeException("Unauthorized");
	}

}
