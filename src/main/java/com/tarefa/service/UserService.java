package com.tarefa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.tarefa.model.Usuario;
import com.tarefa.model.dto.UserDTO;
import com.tarefa.service.util.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	public Usuario cadastrar(@Valid UserDTO dto) {
		var _user = new Usuario(avaliaUsuario(dto));
		repo.save(_user);

		return _user;
	}

	public Page<Usuario> listarUsuarios() {
		return new PageImpl<>(repo.findAll());
	}

	public Usuario detalharPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Id inválido"));
	}

	public Usuario alterarPorId(Long id, @Valid UserDTO dto) {
		var _user = detalharPorId(id);
		_user.alterar(dto);

		return _user;
	}

	public String apagarPorId(Long id) {
		repo.deleteById(id);

		return "Usuario apagado com sucesso";
	}

	private UserDTO avaliaUsuario(UserDTO dto) {
		for (Usuario list : listarUsuarios()) {
			if (list.getUserName().equalsIgnoreCase(dto.userName())) {
				throw new RuntimeException("Já existe um usuario com mesmo nome.");
			}
		}
		return dto;
	}
}
