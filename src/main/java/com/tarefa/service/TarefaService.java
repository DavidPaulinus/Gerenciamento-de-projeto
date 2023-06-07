package com.tarefa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.tarefa.model.Tarefa;
import com.tarefa.model.dto.TarefaDTO;
import com.tarefa.service.util.repository.TarefaRepository;

import jakarta.validation.Valid;

@Service
public class TarefaService {
	@Autowired
	private TarefaRepository repo;

	public Tarefa criar(@Valid TarefaDTO dto) {
		var _taref = new Tarefa(dto);
		repo.save(_taref);
		
		return _taref;
	}

	public Page<Tarefa> listarTarefas() {
		return new PageImpl<>(repo.findAll());
	}
	
}
