package com.fsr.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsr.security.dto.UsuarioDTO;
import com.fsr.security.security.TokenRec;
import com.fsr.security.service.IUsuarioService;

@RestController()
public class UsuarioController {

	private IUsuarioService service;

	public UsuarioController(IUsuarioService service) {
		super();
		this.service = service;
	}

	@PostMapping("/novo-usuario")
	public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody UsuarioDTO userDto) {
		return ResponseEntity.status(201).body(service.addUsuario(userDto));
	}

	@PostMapping("/auth/login")
	public ResponseEntity<TokenRec> login(@RequestBody UsuarioDTO usuarioLogin) {

		return ResponseEntity.status(200).body(service.userLogin(usuarioLogin));
	}

	@GetMapping("/autenticar")
	public ResponseEntity<String> autenticar() {
		return ResponseEntity.status(200).body("Autenticado com sucesso");
	}

}
