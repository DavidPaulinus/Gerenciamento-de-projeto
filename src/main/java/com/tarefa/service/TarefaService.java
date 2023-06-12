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
		var _taref = new Tarefa(avaliaTarefa(dto));
		repo.save(_taref);

		return _taref;
	}

	public Page<Tarefa> listarTarefas() {
		return new PageImpl<>(repo.findAll());
	}

	public Tarefa detalharPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Erro ao achar por ID"));
	}

	public Tarefa alterarPorId(Long id, @Valid TarefaDTO dto) {
		var _taref = detalharPorId(id);
		_taref.alterar(dto);

		return _taref;
	}

	public String apagarPorId(Long id) {
		repo.deleteById(id);

		return "Tarefa apagada com sucesso!";
	}

	private TarefaDTO avaliaTarefa(TarefaDTO dto) {
		for (Tarefa lista : listarTarefas()) {
			if (lista.getNome().equals(dto.nome()) && lista.getPrazo().equals(dto.prazo())) {
				throw new RuntimeException("Não é possível ter mais de um projeto com mesmo nome e prazo");
			}
		}

		return dto;
	}

}
