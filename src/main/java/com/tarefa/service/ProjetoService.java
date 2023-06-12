package com.tarefa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.tarefa.model.Projeto;
import com.tarefa.model.dto.ProjetoDTO;
import com.tarefa.service.util.repository.ProjetoRepository;

import jakarta.validation.Valid;

@Service
public class ProjetoService {
	@Autowired
	private ProjetoRepository repo;
	
	public Projeto criarProjeto(ProjetoDTO dto) {
		var _proj = new Projeto(dto);
		repo.save(_proj);
		
		return _proj;
	}

	public Page<Projeto> listarProjetos() {
		return new PageImpl<>(repo.findAll());
	}

	public Projeto detalharPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("ID inválido"));
	}

	public Projeto alterarPorId(@Valid ProjetoDTO dto, Long id) {
		var _proj = detalharPorId(id);
		_proj.alterar(dto);
		
		return _proj;
	}

	public String apagarPorId(Long id) {
		repo.deleteById(id);
		
		return "Projeto apagado com sucesso";
	}
}
