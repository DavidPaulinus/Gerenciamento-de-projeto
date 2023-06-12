package com.tarefa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefa.model.Projeto;
import com.tarefa.model.dto.ProjetoDTO;
import com.tarefa.service.util.repository.ProjetoRepository;

@Service
public class ProjetoService {
	@Autowired
	private ProjetoRepository repo;
	
	public Projeto criarProjeto(ProjetoDTO dto) {
		var _proj = new Projeto(dto);
		repo.save(_proj);
		
		return _proj;
	}
}
