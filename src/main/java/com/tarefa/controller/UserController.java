package com.tarefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tarefa.model.DetalharUsuario;
import com.tarefa.model.dto.UserDTO;
import com.tarefa.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService serv;

	@PostMapping("/cadastrar")
	@Transactional
	public ResponseEntity<DetalharUsuario> cadastrarUsuario(@RequestBody @Valid UserDTO dto, UriComponentsBuilder uri) {
		var _user = serv.cadastrar(dto);

		return ResponseEntity.created(uri.path("/user/cadastrar").buildAndExpand(_user.getId()).toUri())
				.body(new DetalharUsuario(_user));
	}

}
